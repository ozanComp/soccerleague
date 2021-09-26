package com.sol.soccerleague.di.component

import com.sol.soccerleague.MainActivity
import com.sol.soccerleague.di.module.ApiModule
import com.sol.soccerleague.di.module.RoomModule
import com.sol.soccerleague.di.module.ViewModelModule
import com.sol.soccerleague.ui.fixture.FixtureFragment
import com.sol.soccerleague.ui.fixture.FixtureListFragment
import com.sol.soccerleague.ui.teamlist.TeamListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, ViewModelModule::class, RoomModule::class])
interface AppComponent {
    fun inject(teamListFragment: TeamListFragment)
    fun inject(fixtureListFragment: FixtureListFragment)
    fun inject(fixtureFragment: FixtureFragment)
    fun inject(mainActivity: MainActivity)
}