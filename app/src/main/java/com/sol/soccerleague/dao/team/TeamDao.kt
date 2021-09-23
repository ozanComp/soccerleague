package com.sol.soccerleague.dao.team

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface TeamDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertAll(list : List<TeamEntity>)

    @Query("SELECT * FROM team ")
    suspend fun getAllTeam() : List<TeamEntity>
}