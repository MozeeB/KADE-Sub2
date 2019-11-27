package com.zeeb.footballmatchschedule.data.repository

import com.zeeb.footballmatchschedule.domain.LastMatchDomain
import io.reactivex.Single

interface NextMatchRepository {

    fun getNextMactH(id_league:String) : Single<List<LastMatchDomain>>
}