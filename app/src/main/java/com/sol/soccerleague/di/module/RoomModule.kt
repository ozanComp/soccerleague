package com.sol.soccerleague.di.module

import android.app.Application
import androidx.room.Room
import com.sol.soccerleague.dao.CompetitionsDatabase
import com.sol.soccerleague.dao.CompetitionsDatabaseRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(_application: Application) {
    private var application = _application
    private lateinit var competitionsDatabase: CompetitionsDatabase

    @Singleton
    @Provides
    fun provideCompetitionsDatabase(): CompetitionsDatabase {
        competitionsDatabase = Room.databaseBuilder(application, CompetitionsDatabase::class.java,
            "competitions_database")
            .fallbackToDestructiveMigration()
            .build()
        return competitionsDatabase
    }

    @Singleton
    @Provides
    fun provideCompetitionsDatabaseRepository(competitionsDatabase: CompetitionsDatabase): CompetitionsDatabaseRepository {
        return CompetitionsDatabaseRepository(competitionsDatabase)
    }
}