package com.zeeb.footballmatchschedule.data.repository

import com.zeeb.footballmatchschedule.domain.FootballDetailDomain
import io.reactivex.Single

interface FootballDetailRepository {

    fun getDetailLiga(idLeague:String) : Single<List<FootballDetailDomain>>

}