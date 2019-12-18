package com.zeeb.footballmatchschedule.screen.detail.team

import androidx.lifecycle.MutableLiveData
import com.zeeb.footballmatchschedule.data.repository.TeamsRepository
import com.zeeb.footballmatchschedule.domain.TeamsDomain
import com.zeeb.footballmatchschedule.helper.RxUtils
import com.zeeb.footballmatchschedule.screen.base.BaseViewModel

sealed class TeamsState
data class ErrorState(var msg:String?) : TeamsState()
data class TeamsDataLoaded(val teamsDomain: List<TeamsDomain>) : TeamsState()
class TeamVM (private val teamsRepository: TeamsRepository) : BaseViewModel(){

    val teamsState = MutableLiveData<TeamsState>()

    fun getTeams(id:String){
        compositeDisposable.add(
            teamsRepository.getTeams(id)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()){
                        teamsState.value = TeamsDataLoaded(result)
                    }
                }, this::onError)
        )
    }
    override fun onError(error: Throwable) {
        teamsState.value = ErrorState(error.localizedMessage)
    }

}