package com.sol.soccerleague.dao.fixture

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface FixtureDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertAllFixture(list : List<FixtureEntity>)

    @Query("SELECT * FROM fixture where week = :week")
    suspend fun getFixtureByWeek(week : Int) : List<FixtureEntity>

    @Query("DELETE FROM fixture")
    suspend fun deleteAllFixture()
}