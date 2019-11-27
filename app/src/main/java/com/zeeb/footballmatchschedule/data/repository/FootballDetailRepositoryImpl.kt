package com.zeeb.footballmatchschedule.data.repository

import com.zeeb.footballmatchschedule.data.mapper.FootballDetailMapper
import com.zeeb.footballmatchschedule.data.service.GlobalService
import com.zeeb.footballmatchschedule.domain.FootballDetailDomain
import io.reactivex.Single

class FootballDetailRepositoryImpl(
    private val service: GlobalService,
    private val mapper: FootballDetailMapper
) : FootballDetailRepository {
    override fun getDetailLiga(idLeague:String): Single<List<FootballDetailDomain>> {
        return service.getDetailLiga(idLeague).map {
            mapper.mapToListDomain(it.leagues)
        }
    }
}