package com.zeeb.footballmatchschedule.data.service

import com.zeeb.footballmatchschedule.data.response.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GlobalService {

    @GET("lookupleague.php?")
    fun getDetailLiga(@Query("id") idLeague:String) : Single<TopDetailResponse>

    @GET("eventsnextleague.php?")
    fun getNextMatch(@Query("id") idLeague:String) : Single<TopNextMatchResponse>

    @GET("eventspastleague.php?")
    fun getLastMatch(@Query("id") idLeague:String) : Single<TopLastMatchResponse>

    @GET("lookupevent.php?")
    fun getDetailMatch(@Query("id") idLeague:String ) : Single<TopDetailMatchResponse>

    @GET("searchevents.php?")
    fun searchMatch(@Query("e") argument:String) : Single<TopSearchResponse>

    @GET("lookupteam.php?")
    fun getLogoTeam(@Query("id") id:String) : Single<TopTeamResponse>
}