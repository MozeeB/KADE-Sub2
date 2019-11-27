package com.zeeb.footballmatchschedule.data.repository

import com.zeeb.footballmatchschedule.data.mapper.LastMatchMapper
import com.zeeb.footballmatchschedule.data.service.GlobalService
import com.zeeb.footballmatchschedule.domain.LastMatchDomain
import io.reactivex.Single

class NextMatchRepositoryImpl(
    private val service: GlobalService,
    private val mapper: LastMatchMapper
) : NextMatchRepository{
    override fun getNextMactH(id_league:String): Single<List<LastMatchDomain>> {
        return service.getNextMatch(id_league).map {
            mapper.mapToListDomain(it.events)
        }
    }
}