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
    fun getLogoTeam(@Query("id") id:String) : Single<TopTeamLogoResponse>

    @GET("lookuptable.php?")
    fun getStandings(@Query("l") idLeague: String) : Single<TopStandingResponse>

    @GET("lookup_all_teams.php?")
    fun getTeams(@Query("id") idLeague:String) : Single<TopTeamResponse>

    @GET("lookupteam.php?")
    fun getDetailTeam(@Query("id") idTeam:String) : Single<TopTeamResponse>

    @GET("searchteams.php?")
    fun searchTeams(@Query("t") teams:String) : Single<TopTeamResponse>
}