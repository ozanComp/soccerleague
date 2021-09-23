package com.sol.soccerleague.api

import com.sol.soccerleague.model.Competitions


interface ApiRepository {
    suspend fun getAllTeams(): ApiResultWrapper<Competitions>
}