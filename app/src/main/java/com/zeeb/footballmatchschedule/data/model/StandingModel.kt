package com.zeeb.footballmatchschedule.data.model

class StandingModel(
    val name:String,
    val teamid:String,
    val played:Int,
    val goalsfor:Int,
    val goalsagainst:Int,
    val goalsdifference:Int,
    val win:Int,
    val draw:Int,
    val loss:Int,
    val total:Int
)