package com.zeeb.footballmatchschedule.domain

class DetailMatchDomain(
    val idEvent: String,
    val strEvent: String,
    val strSport: String,
    val strLeague: String,
    val strSeason: String,
    val strHomeTeam: String,
    val strAwayTeam: String,
    val intHomeScore: String?,
    val intRound: String?,
    val intAwayScore: String?,
    val strHomeGoalDetails: String?,
    val strAwayGoalDetails: String?,
    val dateEvent: String,
    val strTime: String
)