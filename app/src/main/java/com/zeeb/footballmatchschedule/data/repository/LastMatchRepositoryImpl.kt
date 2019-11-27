package com.zeeb.footballmatchschedule.data.repository

import com.zeeb.footballmatchschedule.data.mapper.LastMatchMapper
import com.zeeb.footballmatchschedule.data.service.GlobalService
import com.zeeb.footballmatchschedule.domain.LastMatchDomain
import io.reactivex.Single

class LastMatchRepositoryImpl (
    private val service: GlobalService,
    private val lastMatchMapper: LastMatchMapper
) : LastMatchRepository{

    override fun getLastMatch(idLeague: String): Single<List<LastMatchDomain>> {
        return service.getLastMatch(idLeague).map {
            lastMatchMapper.mapToListDomain(it.events)
        }
    }
}