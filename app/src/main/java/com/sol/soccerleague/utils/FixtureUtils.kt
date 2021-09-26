package com.sol.soccerleague.utils

import com.sol.soccerleague.model.Fixture
import com.sol.soccerleague.model.Team

fun createFixture(teamList: List<Team>) : ArrayList<Fixture>{
    var listRaw = (teamList.shuffled() as ArrayList<Team>)
    val fixtureList: ArrayList<Fixture> = arrayListOf()
    var newList: ArrayList<Team>
    val round = teamList.size - 1
    val matchInRound = teamList.size / 2
    var idCount = 0
    var fixtureVariable:Fixture
    listRaw.forEach {
        println(it.name)
    }

    for(i in 0 until round){
        for(j in 0 until matchInRound){
            val team1 = listRaw[j].shortName
            val team2 = listRaw[teamList.size - 1 - j].shortName

            if(j % 2 == 1){
                fixtureVariable = Fixture(idCount,team1, team2,i+1)
                fixtureList.add(fixtureVariable)
                idCount++

                fixtureVariable = Fixture(idCount,team2, team1,i+1 + round)
                fixtureList.add(fixtureVariable)
                idCount++
            }else{
                fixtureVariable = Fixture(idCount,team2, team1,i+1)
                fixtureList.add(fixtureVariable)
                idCount++

                fixtureVariable = Fixture(idCount,team1, team2,i+1 + round)
                fixtureList.add(fixtureVariable)
                idCount++
            }
        }

        newList = arrayListOf()
        newList.add(listRaw[0])
        newList.add(listRaw[listRaw.size-1])

        for(x in 1 until listRaw.size - 1){
            newList.add(listRaw[x])
        }
        listRaw = newList
    }

    fixtureList.sortBy {
        it.week
    }
    return fixtureList
}