package com.zeeb.footballmatchschedule.data.local.model

data class FavoriteTeam(
    val id:Long?,
    val idTeam:String?,
    val strTeam:String?,
    val strSport:String?,
    val strLeague:String?,
    val idLeague:String?,
    val strTeamBadge:String?,
    val strDescriptionEN:String?
){
    companion object {
        const val TABLE_FAV_TEAM: String = "TABLE_FAVORITE_TEAM"
        const val ID: String = "ID_"
        const val ID_TEAM: String = "ID_TEAM"
        const val STR_TEAM = "STR_TEAM"
        const val STR_SPORT = "STR_SPORT"
        const val STR_LEAGUE = "STR_LEAGUE"
        const val ID_LEAGUE = "ID_LEAGUE"
        const val STR_TEAMBADGE = "STR_TEAMBADGE"
        const val STR_DESCRIPTION_EN = "STR_DESCRIPTION_EN"

    }
}