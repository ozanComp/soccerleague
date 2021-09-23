package com.sol.soccerleague.utils

import com.sol.soccerleague.dao.fixture.FixtureEntity
import com.sol.soccerleague.dao.team.TeamEntity
import com.sol.soccerleague.model.Fixture
import com.sol.soccerleague.model.Team

fun List<Fixture>.toFixtureEntityList() = map {
    it.toFixtureEntity()
}

fun Fixture.toFixtureEntity() = FixtureEntity(
    id = id,
    home = homeTeam,
    away = awayTeam,
    week = week
)

fun List<FixtureEntity>.toFixtureList() = map {
    it.toFixture()
}

fun FixtureEntity.toFixture() = Fixture(
    id = id,
    homeTeam = home,
    awayTeam = away,
    week = week
)

fun List<TeamEntity>.toTeamList() = map {
    it.toTeam()
}

fun List<Team>.toTeamEntityList() = map {
    it.toTeamEntity()
}

fun TeamEntity.toTeam() = Team(
    id = id,
    shortName = shortName,
    name = name,
    tla = tla
)

fun Team.toTeamEntity() = TeamEntity(
    id = id,
    shortName = shortName,
    name = name,
    tla = tla
)