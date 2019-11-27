package com.zeeb.footballmatchschedule.data.repository

import com.zeeb.footballmatchschedule.data.mapper.SearchMapper
import com.zeeb.footballmatchschedule.data.service.GlobalService
import com.zeeb.footballmatchschedule.domain.SearchDomain
import io.reactivex.Single

class SearchRepositoryImpl(
    private val service: GlobalService,
    private val searchMapper: SearchMapper
) : SearchRepository{
    override fun searchEvent(argument: String): Single<List<SearchDomain>> {
        return service.searchMatch(argument).map {
            searchMapper.mapToListDomain(it.event)
        }
    }

}