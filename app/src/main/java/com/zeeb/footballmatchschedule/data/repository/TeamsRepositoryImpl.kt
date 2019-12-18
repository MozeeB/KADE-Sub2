package com.zeeb.footballmatchschedule.data.repository

import com.zeeb.footballmatchschedule.data.mapper.TeamsMapper
import com.zeeb.footballmatchschedule.data.service.GlobalService
import com.zeeb.footballmatchschedule.domain.TeamsDomain
import io.reactivex.Single

class TeamsRepositoryImpl(
    private val globalService: GlobalService,
    private val teamsMapper: TeamsMapper
) : TeamsRepository{
    override fun getTeams(id: String): Single<List<TeamsDomain>> {
        return globalService.getTeams(id).map {
            teamsMapper.mapToListDomain(it.teams)
        }
    }

    override fun getDetailTeams(idTeam: String): Single<List<TeamsDomain>> {
        return globalService.getDetailTeam(idTeam).map {
            teamsMapper.mapToListDomain(it.teams)
        }
    }

    override fun searchTeams(team: String): Single<List<TeamsDomain>> {
        return globalService.searchTeams(team).map {
            teamsMapper.mapToListDomain(it.teams)
        }
    }
}