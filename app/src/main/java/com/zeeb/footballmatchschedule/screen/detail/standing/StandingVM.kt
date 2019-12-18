package com.zeeb.footballmatchschedule.screen.detail.standing

import androidx.lifecycle.MutableLiveData
import com.zeeb.footballmatchschedule.data.repository.StandingRepository
import com.zeeb.footballmatchschedule.domain.StandingDomain
import com.zeeb.footballmatchschedule.helper.RxUtils
import com.zeeb.footballmatchschedule.screen.base.BaseViewModel

sealed class StandingState
data class ErrorState(var msg:String?) : StandingState()
data class StandingDataLoaded(val standingDomain: List<StandingDomain>) : StandingState()
class StandingVM (val standingRepository: StandingRepository) : BaseViewModel(){

    val standingState = MutableLiveData<StandingState>()

    fun getStandings(idLeague:String){
        compositeDisposable.add(
            standingRepository.getStanding(idLeague)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if(result.isNotEmpty()){
                        standingState.value = StandingDataLoaded(result)
                    }
                }, this::onError)
        )
    }

    override fun onError(error: Throwable) {
        standingState.value = ErrorState(error.localizedMessage)
    }

}