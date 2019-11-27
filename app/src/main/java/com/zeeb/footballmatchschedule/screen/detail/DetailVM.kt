package com.zeeb.footballmatchschedule.screen.detail

import androidx.lifecycle.MutableLiveData
import com.zeeb.footballmatchschedule.data.repository.FootballDetailRepository
import com.zeeb.footballmatchschedule.domain.FootballDetailDomain
import com.zeeb.footballmatchschedule.helper.RxUtils
import com.zeeb.footballmatchschedule.screen.base.BaseViewModel

sealed class FootballDetailState
data class ErrorState(var msg:String?) : FootballDetailState()
data class DetailDataLoaded(val footballDetailDomain: List<FootballDetailDomain>) : FootballDetailState()
class DetailVM (val repo: FootballDetailRepository) : BaseViewModel(){

    val detailState = MutableLiveData<FootballDetailState>()

    fun getDetailLiga(idLeague:String){
        compositeDisposable.add(
            repo.getDetailLiga(idLeague)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    detailState.value = DetailDataLoaded(result)
                }, this::onError)
        )
    }
    override fun onError(error: Throwable) {
        detailState.value = ErrorState(error.localizedMessage)

    }

}
