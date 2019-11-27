package com.zeeb.footballmatchschedule.screen.detail.lastmatch

import androidx.lifecycle.MutableLiveData
import com.zeeb.footballmatchschedule.data.repository.LastMatchRepository
import com.zeeb.footballmatchschedule.domain.LastMatchDomain
import com.zeeb.footballmatchschedule.helper.RxUtils
import com.zeeb.footballmatchschedule.screen.base.BaseViewModel

sealed class LastMatchState
data class ErrorState(var msg:String?) : LastMatchState()
data class LastMatchDataLoaded(val lastMatchDomain: List<LastMatchDomain>) : LastMatchState()
class LastMatchVM (val repo:LastMatchRepository) : BaseViewModel(){

    val lastMatchState = MutableLiveData<LastMatchState>()

    fun getLastMatch(id:String){
        compositeDisposable.add(
            repo.getLastMatch(id)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    lastMatchState.value = LastMatchDataLoaded(result)
                }, this::onError)
        )
    }

    override fun onError(error: Throwable) {
        lastMatchState.value = ErrorState(error.localizedMessage)
    }

}
