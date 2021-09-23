package com.sol.soccerleague.ui.teamlist

import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sol.soccerleague.api.ApiRepositoryImpl
import com.sol.soccerleague.api.ApiResultWrapper
import com.sol.soccerleague.model.Team
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class TeamListViewModel @Inject constructor(private val apiRepositoryImpl: ApiRepositoryImpl)
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

    private val _temList = MutableLiveData<List<Team>?>()
    val teamList: LiveData<List<Team>?>
        get() = _temList

    init {
        _isLoading.postValue(false)
        _isApiError.postValue(false)
    }

    fun getAllTeams(){
        uiScope.launch {
            println("try get all teams...")

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
                    println("response team count ${response.value.teams?.size}")
                    _temList.postValue(response.value.teams)
                }
            }
        }
    }

}