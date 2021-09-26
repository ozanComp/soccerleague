package com.sol.soccerleague.ui.teamlist

import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sol.soccerleague.api.ApiRepositoryImpl
import com.sol.soccerleague.api.ApiResultWrapper
import com.sol.soccerleague.dao.CompetitionsDatabaseRepository
import com.sol.soccerleague.model.Team
import com.sol.soccerleague.utils.createFixture
import com.sol.soccerleague.utils.toFixtureEntityList
import com.sol.soccerleague.utils.toTeamEntityList
import com.sol.soccerleague.utils.toTeamList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class TeamListViewModel @Inject constructor(private val apiRepositoryImpl: ApiRepositoryImpl,
                                            private val competitionsDatabaseRepository: CompetitionsDatabaseRepository)
    : ViewModel(), Observable {

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _isApiError = MutableLiveData<Boolean>()
    val isApiError: LiveData<Boolean>
        get() = _isApiError
    fun doneApiError(){
        _isApiError.postValue(false)
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading
    fun doneLoading(){
        _isLoading.postValue(false)
    }

    private val _hasCurrentFixture = MutableLiveData<Boolean>()
    val hasCurrentFixture: LiveData<Boolean>
        get() = _hasCurrentFixture

    private val _hasTeamFromLocal = MutableLiveData<Boolean>()
    val hasTeamFromLocal: LiveData<Boolean>
        get() = _hasTeamFromLocal

    private val _showFixture = MutableLiveData<Boolean>()
    val showFixture: LiveData<Boolean>
        get() = _showFixture


    private val _teamList = MutableLiveData<List<Team>?>()
    val teamList: LiveData<List<Team>?>
        get() = _teamList

    fun getAllTeamFromLocal(){
        uiScope.launch {
            println("try get all team from local...")

            val response = competitionsDatabaseRepository.getAllTeams()
            if(response.isNullOrEmpty()){
                _hasTeamFromLocal.postValue(false)
            }else{
                _hasTeamFromLocal.postValue(true)
                _teamList.postValue(response.toTeamList())
            }
        }
    }

    fun getAllTeams(){
        uiScope.launch {
            println("try get all teams...")

            _isLoading.postValue(true)

            when(val response = apiRepositoryImpl.getAllTeams()){
                is ApiResultWrapper.GenericError ->{
                    println("network error")
                    _isApiError.postValue(true)
                }
                is ApiResultWrapper.NetworkError ->{
                    println("generic error")
                    _isApiError.postValue(true)
                }
                is ApiResultWrapper.Success ->{
                    response.value.teams?.let {
                        println("response team count ${response.value.teams.size}")
                        competitionsDatabaseRepository.insertAllTeams(it.toTeamEntityList()) }
                    _teamList.postValue(response.value.teams)
                }
            }
        }
    }

    fun drawFixture(){
        uiScope.launch {
            println("try draw fixture")

            val response = _teamList.value?.let { createFixture(it) }
            if (response != null) {
                competitionsDatabaseRepository.deleteAllFixture()
                competitionsDatabaseRepository.insertAllFixture(response.toFixtureEntityList())
                showFixture()
            }
        }
    }

    fun showFixture(){
        uiScope.launch {
            println("try show current fixture")
            _showFixture.postValue(true)
        }
    }

    fun getCurrentFixture(){
        uiScope.launch {
            println("try get fixture...")

            val response = competitionsDatabaseRepository.getFixtureByWeek(1)
            if(response.isNullOrEmpty()){
                _hasCurrentFixture.postValue(false)
            }else{
                _hasCurrentFixture.postValue(true)
            }
        }
    }
}