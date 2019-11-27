package com.zeeb.footballmatchschedule.data.repository

import com.zeeb.footballmatchschedule.domain.DetailMatchDomain
import com.zeeb.footballmatchschedule.domain.TeamDomain
import io.reactivex.Single

interface DetailMatchRepository {
    fun getDetailMatch(idLeague:String) : Single<List<DetailMatchDomain>>

    fun getLogoHome(idHome:String) : Single<List<TeamDomain>>

    fun getLogoAway(idAway:String) : Single<List<TeamDomain>>
}