package com.zeeb.footballmatchschedule.data.repository

import com.zeeb.footballmatchschedule.data.mapper.DetailMatchMapper
import com.zeeb.footballmatchschedule.data.mapper.TeamLogoMapper
import com.zeeb.footballmatchschedule.data.service.GlobalService
import com.zeeb.footballmatchschedule.domain.DetailMatchDomain
import com.zeeb.footballmatchschedule.domain.TeamLogoDomain
import io.reactivex.Single

class DetailMatchRepositoryImpl(
    private val service: GlobalService,
    private val detailMatchMapper: DetailMatchMapper,
    private val teamLogoMapper: TeamLogoMapper
) : DetailMatchRepository{
    override fun getDetailMatch(idLeague: String): Single<List<DetailMatchDomain>> {
        return  service.getDetailMatch(idLeague).map {
            detailMatchMapper.mapToListDomain(it.events)
        }
    }

    override fun getLogoHome(idHome: String): Single<List<TeamLogoDomain>> {
        return service.getLogoTeam(idHome).map {
            teamLogoMapper.mapToListDomain(it.teamLogos)
        }
    }

    override fun getLogoAway(idAway: String): Single<List<TeamLogoDomain>> {
        return service.getLogoTeam(idAway).map {
            teamLogoMapper.mapToListDomain(it.teamLogos)
        }
    }
}