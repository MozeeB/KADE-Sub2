package com.zeeb.footballmatchschedule.screen.detail.search

import androidx.lifecycle.MutableLiveData
import com.zeeb.footballmatchschedule.data.repository.SearchRepository
import com.zeeb.footballmatchschedule.domain.SearchDomain
import com.zeeb.footballmatchschedule.helper.RxUtils
import com.zeeb.footballmatchschedule.screen.base.BaseViewModel

sealed class SearchState
data class ErrorState(var msg:String?) : SearchState()
data class SearchDataLoaded(val searchDomain: List<SearchDomain>) : SearchState()
class SearchVM (val repository: SearchRepository) : BaseViewModel(){

    val searchState = MutableLiveData<SearchState>()

    fun searchEvent(argument:String){
        compositeDisposable.add(
            repository.searchEvent(argument)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()){
                        searchState.value = SearchDataLoaded(result.filter { it.strSport == "Soccer" })
                    }
                }, this::onError)
        )
    }

    override fun onError(error: Throwable) {
        searchState.value = ErrorState(error.localizedMessage)
    }

}
