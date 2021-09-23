package com.sol.soccerleague.di.module

import androidx.lifecycle.ViewModel
import com.sol.soccerleague.di.factory.ViewModelKey
import com.sol.soccerleague.ui.fixture.FixtureViewModel
import com.sol.soccerleague.ui.teamlist.TeamListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(TeamListViewModel::class)
    internal abstract fun bindTeamListViewModel(viewModel: TeamListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FixtureViewModel::class)
    internal abstract fun bindFixtureViewModel(viewModel: FixtureViewModel): ViewModel
}