package com.zeeb.footballmatchschedule.screen.detail.detailmatch

import androidx.lifecycle.MutableLiveData
import com.zeeb.footballmatchschedule.data.repository.DetailMatchRepository
import com.zeeb.footballmatchschedule.domain.DetailMatchDomain
import com.zeeb.footballmatchschedule.domain.TeamDomain
import com.zeeb.footballmatchschedule.helper.RxUtils
import com.zeeb.footballmatchschedule.screen.base.BaseViewModel

sealed class DetailMatchState
data class ErrorState(var msg:String?) : DetailMatchState()
data class DetailMatchDataLoaded(val detailMatchDomain: List<DetailMatchDomain>) : DetailMatchState()
data class LogoHomeLoaded(val teamDomain: List<TeamDomain>) : DetailMatchState()
data class LogoAwayLoaded(val teamDomain: List<TeamDomain>) : DetailMatchState()
class DetailMatchVM (val repository: DetailMatchRepository) : BaseViewModel(){

    val detailMatchStatev = MutableLiveData<DetailMatchState>()


    fun getDetailMatch(id:String){
        compositeDisposable.add(
            repository.getDetailMatch(id)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()){
                        detailMatchStatev.value = DetailMatchDataLoaded(result)
                    }
                }, this::onError)
        )
    }

    fun getLogoHome(id:String){
        compositeDisposable.add(
            repository.getLogoHome(id)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()){
                        detailMatchStatev.value = LogoHomeLoaded(result)
                    }
                }, this::onError)
        )
    }

    fun getLogoAway(id:String){
        compositeDisposable.add(
            repository.getLogoAway(id)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    detailMatchStatev.value = LogoAwayLoaded(result)
                }, this::onError)
        )
    }
    override fun onError(error: Throwable) {
        detailMatchStatev.value = ErrorState(error.localizedMessage)
    }

}
