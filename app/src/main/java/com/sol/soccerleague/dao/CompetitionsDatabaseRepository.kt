package com.sol.soccerleague.dao

import com.sol.soccerleague.dao.fixture.FixtureDao
import com.sol.soccerleague.dao.fixture.FixtureEntity
import com.sol.soccerleague.dao.team.TeamDao
import com.sol.soccerleague.dao.team.TeamEntity

class CompetitionsDatabaseRepository(competitionsDatabase: CompetitionsDatabase) {
    private var teamDao:TeamDao = competitionsDatabase.teamDao()
    private var fixtureDao:FixtureDao = competitionsDatabase.fixtureDao()

    suspend fun insertAllTeams(list : List<TeamEntity>){
        teamDao.insertAll(list)
    }

    suspend fun getAllTeams(): List<TeamEntity>{
        return teamDao.getAllTeam()
    }

    suspend fun deleteAllFixture(){
        fixtureDao.deleteAllFixture()
    }

    suspend fun insertAllFixture(list : List<FixtureEntity>){
        fixtureDao.insertAllFixture(list)
    }

    suspend fun getFixtureByWeek(week: Int): List<FixtureEntity>{
        return fixtureDao.getFixtureByWeek(week)
    }
}