package com.zeeb.footballmatchschedule.data.local.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.zeeb.footballmatchschedule.data.local.model.FavoriteLastMatch
import com.zeeb.footballmatchschedule.data.local.model.FavoriteTeam
import org.jetbrains.anko.db.*

class FootballDatabaseHelper(ctx: Context) :
    ManagedSQLiteOpenHelper(ctx, "FootballApp.db",null,1){

    companion object {
        private  var instance: FootballDatabaseHelper? = null

        fun getInstance(ctx: Context): FootballDatabaseHelper{
            if (instance == null) {
                instance = FootballDatabaseHelper(ctx.applicationContext)
            }
            return instance as FootballDatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            FavoriteLastMatch.TABLE_FAV_MATCH,true,
            FavoriteLastMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteLastMatch.ID_EVENT to TEXT + UNIQUE,
            FavoriteLastMatch.DATE_EVENT to TEXT,
            FavoriteLastMatch.HOME_SCORE to TEXT,
            FavoriteLastMatch.AWAY_SCORE to TEXT,
            FavoriteLastMatch.NAME_EVENT to TEXT,
            FavoriteLastMatch.LEAGUE to TEXT,
            FavoriteLastMatch.ID_HOME_TEAM to TEXT,
            FavoriteLastMatch.ID_AWAY_TEAM to TEXT)

        db.createTable(
            FavoriteTeam.TABLE_FAV_TEAM, true,
            FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteTeam.ID_TEAM to TEXT + UNIQUE,
            FavoriteTeam.STR_TEAM to TEXT,
            FavoriteTeam.STR_SPORT to TEXT,
            FavoriteTeam.STR_LEAGUE to TEXT,
            FavoriteTeam.ID_LEAGUE to TEXT,
            FavoriteTeam.STR_TEAMBADGE to TEXT,
            FavoriteTeam.STR_DESCRIPTION_EN to TEXT
        )


    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(FavoriteLastMatch.TABLE_FAV_MATCH,true)

        db.dropTable(FavoriteTeam.TABLE_FAV_TEAM, true)
    }

}
val Context.database: FootballDatabaseHelper
    get() = FootballDatabaseHelper.getInstance(applicationContext)