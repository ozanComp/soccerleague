package com.sol.soccerleague.utils

import com.sol.soccerleague.model.Fixture
import com.sol.soccerleague.model.Team

private fun shuffledList(teamList: List<Team>) : ArrayList<Team> {
    var shuffled = teamList.shuffled()

    if (shuffled.size % 2 == 1){
        shuffled = shuffled.drop(1)
    }

    return (shuffled as ArrayList<Team>?)!!
}

fun createFixture(teamList: List<Team>) : ArrayList<Fixture>{
    var listRaw = shuffledList(teamList)
    val fixtureList : ArrayList<Fixture> = arrayListOf()
    val newList  : ArrayList<Team> = arrayListOf()
    val round = teamList.size - 1
    val matchInRound = teamList.size / 2
    var idCount = 0

    for(i in 0..round){
        for(j in 0 until matchInRound){
            val team1 = listRaw[j].shortName
            val team2 = listRaw[teamList.size -1 -j].shortName
            val fixtureVariable = Fixture(idCount, team1, team2,i + 1)
            fixtureList.add(fixtureVariable)
            idCount++
        }

        newList.add(listRaw[0])
        newList.add(listRaw[listRaw.size-1])

        for(j in 1 until listRaw.size){
            newList.add(listRaw[j])
        }
        listRaw = newList
    }
    return fixtureList
}