package com.sol.soccerleague

import com.sol.soccerleague.model.Team
import com.sol.soccerleague.utils.createFixture
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class FixtureUnitTest: TestWatcher() {
    private val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun whenCreateFixture() {
        val teamList: ArrayList<Team> = ArrayList()
        for (i in 0 until 4){
            teamList.add(Team(i, "Test$i", "T$i", "TLA$i"))
        }

        val result = createFixture(teamList.toList())
        println("Fixture size " + result.size)
        result.forEach {
            println("id: ${it.id} week: ${it.week} home: ${it.homeTeam} away:${it.awayTeam}")
        }
    }
}
