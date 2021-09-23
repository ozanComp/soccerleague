package com.sol.soccerleague.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sol.soccerleague.dao.fixture.FixtureDao
import com.sol.soccerleague.dao.fixture.FixtureEntity
import com.sol.soccerleague.dao.team.TeamDao
import com.sol.soccerleague.dao.team.TeamEntity

@Database(entities = [TeamEntity::class, FixtureEntity::class], version = 1, exportSchema = false)
abstract class CompetitionsDatabase : RoomDatabase() {
    abstract fun teamDao() : TeamDao
    abstract fun fixtureDao() : FixtureDao
}
