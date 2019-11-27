package com.zeeb.footballmatchschedule.screen.detail.nextmatch

import androidx.lifecycle.MutableLiveData
import com.zeeb.footballmatchschedule.data.repository.NextMatchRepository
import com.zeeb.footballmatchschedule.domain.LastMatchDomain
import com.zeeb.footballmatchschedule.helper.RxUtils
import com.zeeb.footballmatchschedule.screen.base.BaseViewModel

sealed class NextMatchState
data class ErrorState(var msg:String?) : NextMatchState()
data class NextMatchDataLoaded(val lastMatchDomain: List<LastMatchDomain>) : NextMatchState()
class NextMatchVM (val repository: NextMatchRepository) : BaseViewModel(){

    val nextMatchState = MutableLiveData<NextMatchState>()

    fun getNextMatch(id_league:String){
        compositeDisposable.add(
            repository.getNextMactH(id_league)
                .compose(RxUtils.applySingleAsync())
                .subscribe({
                    nextMatchState.value = NextMatchDataLoaded(it)
                }, this::onError)
        )
    }

    override fun onError(error: Throwable) {
        nextMatchState.value = ErrorState(error.localizedMessage)
    }

}
