package com.sol.soccerleague.api

import com.sol.soccerleague.api.ApiConstant.GET_ALL_TEAMS
import com.sol.soccerleague.model.Competitions
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET(GET_ALL_TEAMS)
    suspend fun getAllTeam(@Header("X-Auth-Token") key: String): Competitions
}