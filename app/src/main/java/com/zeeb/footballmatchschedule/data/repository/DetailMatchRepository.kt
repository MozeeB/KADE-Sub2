package com.zeeb.footballmatchschedule.data.repository

import com.zeeb.footballmatchschedule.domain.DetailMatchDomain
import com.zeeb.footballmatchschedule.domain.TeamLogoDomain
import io.reactivex.Single

interface DetailMatchRepository {
    fun getDetailMatch(idLeague:String) : Single<List<DetailMatchDomain>>

    fun getLogoHome(idHome:String) : Single<List<TeamLogoDomain>>

    fun getLogoAway(idAway:String) : Single<List<TeamLogoDomain>>
}