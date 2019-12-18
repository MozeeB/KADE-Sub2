package com.zeeb.footballmatchschedule.screen.detail.search.team

import androidx.lifecycle.MutableLiveData
import com.zeeb.footballmatchschedule.data.repository.TeamsRepository
import com.zeeb.footballmatchschedule.domain.TeamsDomain
import com.zeeb.footballmatchschedule.helper.RxUtils
import com.zeeb.footballmatchschedule.screen.base.BaseViewModel

sealed class SearchTeamState
data class ErrorState(var msg:String?) : SearchTeamState()
data class SearchTeamDataLoaded(val teamsDomain: List<TeamsDomain>) : SearchTeamState()
class SearchTeamVM (val repository: TeamsRepository) : BaseViewModel(){

    val searchTeamState = MutableLiveData<SearchTeamState>()

    fun getSearchTeam(team:String){
        compositeDisposable.add(
            repository.searchTeams(team)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()){
                        searchTeamState.value = SearchTeamDataLoaded(result)
                    }
                }, this::onError)
        )
    }
    override fun onError(error: Throwable) {
        searchTeamState.value = ErrorState(error.localizedMessage)
    }

}
