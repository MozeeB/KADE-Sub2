package com.zeeb.footballmatchschedule.screen.detail.detailteam

import androidx.lifecycle.MutableLiveData
import com.zeeb.footballmatchschedule.data.local.dao.TeamDao
import com.zeeb.footballmatchschedule.data.repository.TeamsRepository
import com.zeeb.footballmatchschedule.domain.TeamsDomain
import com.zeeb.footballmatchschedule.helper.RxUtils
import com.zeeb.footballmatchschedule.screen.base.BaseViewModel
import java.util.concurrent.Executor

sealed class DetailTeamState
data class ErrorState(var msg: String?) : DetailTeamState()
data class DetailTeamDataLoaded(val teamsDomain: List<TeamsDomain>) : DetailTeamState()
object FavAddSuccess : DetailTeamState()
object FavRemoveSuccess : DetailTeamState()
data class FavTeamDataFound(val teamsDomain: List<TeamsDomain>) : DetailTeamState()
class DetailTeamVM(val repository: TeamsRepository, private val teamDao: TeamDao, private val executor: Executor) : BaseViewModel() {

    val detailTeamState = MutableLiveData<DetailTeamState>()

    fun getDetailTeams(idTeam: String) {
        compositeDisposable.add(
            repository.getDetailTeams(idTeam)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()) {
                        detailTeamState.value = DetailTeamDataLoaded(result)
                    }
                }, this::onError)
        )
    }

    override fun onError(error: Throwable) {
        detailTeamState.value = ErrorState(error.localizedMessage)
    }

    fun addToFav(teamsDomain: TeamsDomain) {
        executor.execute { teamDao.saveFavMovies(teamsDomain)  }
        detailTeamState.value = FavAddSuccess
    }

    fun removeFav(idTeam: String){
        executor.execute { teamDao.removeMovie(idTeam) }
        detailTeamState.value = FavRemoveSuccess
    }

    fun checkFavState(idTeam: String){
        compositeDisposable.add(
            teamDao.getFavMoviesById(idTeam)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()){
                        detailTeamState.value = FavTeamDataFound(result)
                    }
                }, this::onError)
        )
    }

}
