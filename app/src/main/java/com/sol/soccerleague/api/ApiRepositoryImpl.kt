package com.sol.soccerleague.api

import com.sol.soccerleague.api.ApiConstant.API_KEY
import com.sol.soccerleague.model.Competitions
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val api: ApiService): ApiRepository{
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun getAllTeams(): ApiResultWrapper<Competitions> {
        return safeApiCall(dispatcher) { api.getAllTeam(API_KEY) }
    }
}