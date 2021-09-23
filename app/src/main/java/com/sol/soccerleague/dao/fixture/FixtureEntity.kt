package com.sol.soccerleague.dao.fixture

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "fixture")
data class FixtureEntity(
    @PrimaryKey
    val id : Int,

    @ColumnInfo(name = "home")
    val home: String,

    @ColumnInfo(name = "away")
    val away: String,

    @ColumnInfo(name = "week")
    val week: Int
): Parcelable