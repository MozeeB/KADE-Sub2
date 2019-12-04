package com.zeeb.footballmatchschedule.data.local.model

data class FavoriteLastMatch(val id: Long?,
                             val idEvent: String?,
                             val dateEvent: String?,
                             val homeScore: String?,
                             val awayScore: String?,
                             val strEvent:String?,
                             val strLeague:String?,
                             val idHomeTeam:String?,
                             val idAwayTeam:String?){

    companion object {
        const val TABLE_FAV_MATCH: String = "TABLE_FAVORITE_MATCH"
        const val ID: String = "ID_"
        const val ID_EVENT = "ID_EVENT"
        const val DATE_EVENT = "DATE_EVENT"
        const val HOME_SCORE = "HOME_SCORE"
        const val AWAY_SCORE = "AWAY_SCORE"
        const val NAME_EVENT = "NAME_EVENT"
        const val LEAGUE = "LEAGUE"
        const val ID_HOME_TEAM = "ID_HOME_TEAM"
        const val ID_AWAY_TEAM = "ID_AWAY_TEAM"
    }
}