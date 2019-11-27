package com.zeeb.footballmatchschedule.data.repository

import com.zeeb.footballmatchschedule.data.mapper.DetailMatchMapper
import com.zeeb.footballmatchschedule.data.mapper.TeamMapper
import com.zeeb.footballmatchschedule.data.service.GlobalService
import com.zeeb.footballmatchschedule.domain.DetailMatchDomain
import com.zeeb.footballmatchschedule.domain.TeamDomain
import io.reactivex.Single

class DetailMatchRepositoryImpl(
    private val service: GlobalService,
    private val detailMatchMapper: DetailMatchMapper,
    private val teamMapper: TeamMapper
) : DetailMatchRepository{
    override fun getDetailMatch(idLeague: String): Single<List<DetailMatchDomain>> {
        return  service.getDetailMatch(idLeague).map {
            detailMatchMapper.mapToListDomain(it.events)
        }
    }

    override fun getLogoHome(idHome: String): Single<List<TeamDomain>> {
        return service.getLogoTeam(idHome).map {
            teamMapper.mapToListDomain(it.teams)
        }
    }

    override fun getLogoAway(idAway: String): Single<List<TeamDomain>> {
        return service.getLogoTeam(idAway).map {
            teamMapper.mapToListDomain(it.teams)
        }
    }
}