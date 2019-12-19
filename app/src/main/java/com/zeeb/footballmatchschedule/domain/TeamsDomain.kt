package com.zeeb.footballmatchschedule.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_teams")
class TeamsDomain(

    @PrimaryKey
    @ColumnInfo(name = "idTeam")
    val idTeam:String,
    @ColumnInfo(name = "strTeam")
    val strTeam:String,
    @ColumnInfo(name = "strSport")
    val strSport:String,
    @ColumnInfo(name = "strLeague")
    val strLeague:String,
    @ColumnInfo(name = "idLeague")
    val idLeague:String,
    @ColumnInfo(name = "strTeamBadge")
    val strTeamBadge:String,
    @ColumnInfo(name = "strDescriptionEN")
    val strDescriptionEN:String?


)