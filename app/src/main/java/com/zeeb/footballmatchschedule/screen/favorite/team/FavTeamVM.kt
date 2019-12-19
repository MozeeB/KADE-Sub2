package com.zeeb.footballmatchschedule.screen.favorite.team

import androidx.lifecycle.MutableLiveData
import com.zeeb.footballmatchschedule.data.local.dao.TeamDao
import com.zeeb.footballmatchschedule.domain.TeamsDomain
import com.zeeb.footballmatchschedule.helper.RxUtils
import com.zeeb.footballmatchschedule.screen.base.BaseViewModel

sealed class FavTeamState
data class ErrorState(var msg:String?) : FavTeamState()
data class FavTeamDataLoaded(val teamsDomain: List<TeamsDomain>) : FavTeamState()
class FavTeamVM (val teamsDao: TeamDao) : BaseViewModel(){

    val favTeamState = MutableLiveData<FavTeamState>()

    fun getTeamFav(){
        compositeDisposable.add(
            teamsDao.getTeams()
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()){
                        favTeamState.value = FavTeamDataLoaded(result)
                    }
                }, this::onError)
        )
    }
    override fun onError(error: Throwable) {
        favTeamState.value = ErrorState(error.localizedMessage)
    }

}