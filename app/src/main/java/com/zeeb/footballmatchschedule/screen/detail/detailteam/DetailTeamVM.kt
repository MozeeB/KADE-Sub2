package com.zeeb.footballmatchschedule.screen.detail.detailteam

import androidx.lifecycle.MutableLiveData
import com.zeeb.footballmatchschedule.data.repository.TeamsRepository
import com.zeeb.footballmatchschedule.domain.TeamsDomain
import com.zeeb.footballmatchschedule.helper.RxUtils
import com.zeeb.footballmatchschedule.screen.base.BaseViewModel

sealed class DetailTeamState
data class ErrorState(var msg:String?) : DetailTeamState()
data class DetailTeamDataLoaded(val teamsDomain: List<TeamsDomain>) : DetailTeamState()
class DetailTeamVM(val repository: TeamsRepository) : BaseViewModel(){

    val detailTeamState = MutableLiveData<DetailTeamState>()

    fun getDetailTeams(idTeam:String){
        compositeDisposable.add(
            repository.getDetailTeams(idTeam)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()){
                        detailTeamState.value = DetailTeamDataLoaded(result)
                    }
                }, this::onError)
        )
    }
    override fun onError(error: Throwable) {
        detailTeamState.value = ErrorState(error.localizedMessage)
    }

}
