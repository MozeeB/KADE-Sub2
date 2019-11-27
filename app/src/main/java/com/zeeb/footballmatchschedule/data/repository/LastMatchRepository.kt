package com.zeeb.footballmatchschedule.data.repository

import com.zeeb.footballmatchschedule.domain.LastMatchDomain
import io.reactivex.Single

interface LastMatchRepository {
    fun getLastMatch(idLeague:String) : Single<List<LastMatchDomain>>
}