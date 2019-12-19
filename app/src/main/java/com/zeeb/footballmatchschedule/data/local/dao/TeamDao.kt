package com.zeeb.footballmatchschedule.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zeeb.footballmatchschedule.domain.TeamsDomain
import io.reactivex.Single

@Dao
interface TeamDao {

    @Query("SELECT * FROM tb_teams")
    fun getTeams() : Single<List<TeamsDomain>>

    @Query("SELECT * FROM tb_teams WHERE idTeam=:idTeam")
    fun  getFavMoviesById(idTeam: String) : Single<List<TeamsDomain>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFavMovies(teamsDomain: TeamsDomain)

    @Query("DELETE from tb_teams where idTeam=:idTeam ")
    fun removeMovie(idTeam: String)



}