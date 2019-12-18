package com.zeeb.footballmatchschedule.data.repository

import com.zeeb.footballmatchschedule.domain.StandingDomain
import io.reactivex.Single

interface StandingRepository {
    fun getStanding(idLeague:String) : Single<List<StandingDomain>>
}