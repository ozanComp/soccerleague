package com.sol.soccerleague.ui.fixture

import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sol.soccerleague.dao.CompetitionsDatabaseRepository
import com.sol.soccerleague.model.Fixture
import com.sol.soccerleague.utils.toFixtureList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class FixtureViewModel @Inject constructor(private val competitionsDatabaseRepository: CompetitionsDatabaseRepository)
    : ViewModel(), Observable {

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading
    fun doneLoading(){
        _isLoading.postValue(false)
    }

    private val _fixtureCount = MutableLiveData<Int>()
    val fixtureCount: LiveData<Int>
        get() = _fixtureCount

    private val _fixture = MutableLiveData<List<Fixture>>()
    val fixture: LiveData<List<Fixture>>
        get() = _fixture

    fun getFixtureCount(){
        uiScope.launch {
            _isLoading.postValue(true)

            _fixtureCount.postValue(34)
        }
    }

    fun getFixtureByWeek(week: Int){
        uiScope.launch {
            println("try get $week. week fixture")

            val response = competitionsDatabaseRepository.getFixtureByWeek(week)
            if(!response.isNullOrEmpty()){
                _fixture.postValue(response.toFixtureList())
            }
        }
    }
}