package com.zeeb.footballmatchschedule.data.repository

import com.zeeb.footballmatchschedule.domain.TeamsDomain
import io.reactivex.Single

interface TeamsRepository {
    fun getTeams(id:String) : Single<List<TeamsDomain>>

    fun getDetailTeams(idTeam:String) : Single<List<TeamsDomain>>

    fun searchTeams(team:String) : Single<List<TeamsDomain>>
}