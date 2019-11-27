package com.zeeb.footballmatchschedule.data.repository

import com.zeeb.footballmatchschedule.domain.SearchDomain
import io.reactivex.Single

interface SearchRepository {
    fun searchEvent(argument:String) : Single<List<SearchDomain>>
}