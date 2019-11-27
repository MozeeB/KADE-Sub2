package com.zeeb.footballmatchschedule.data.model

class LastMatchModel (
    val idEvent:String,
    val strEvent:String,
    val strLeague:String,
    val strSeason:String,
    val strHomeTeam:String,
    val strAwayTeam:String,

    val intHomeScore:String?,

    val intRound:String,
    val intAwayScore:String?,

    val dateEvent:String,
    val strTime:String,
    val idHomeTeam:String,
    val idAwayTeam:String
)