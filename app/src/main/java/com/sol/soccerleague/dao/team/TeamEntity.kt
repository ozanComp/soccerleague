package com.sol.soccerleague.dao.team

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "team")
data class TeamEntity(
    @PrimaryKey
    val id : Int,

    @ColumnInfo(name = "name")
    val name : String,

    @ColumnInfo(name = "shortName")
    val shortName : String,

    @ColumnInfo(name = "tla")
    val tla : String
): Parcelable