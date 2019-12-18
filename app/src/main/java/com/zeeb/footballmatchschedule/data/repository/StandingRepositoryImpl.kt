package com.zeeb.footballmatchschedule.data.repository

import com.zeeb.footballmatchschedule.data.mapper.StandingMapper
import com.zeeb.footballmatchschedule.data.service.GlobalService
import com.zeeb.footballmatchschedule.domain.StandingDomain
import io.reactivex.Single

class StandingRepositoryImpl(
    private val service: GlobalService,
    private val standingMapper: StandingMapper
) : StandingRepository{
    override fun getStanding(idLeague: String): Single<List<StandingDomain>> {
        return service.getStandings(idLeague).map {
            standingMapper.mapToListDomain(it.table)
        }
    }
}