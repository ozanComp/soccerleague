package com.sol.soccerleague.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Fixture (
    val id : Int,
    val homeTeam : String,
    val awayTeam : String,
    val week : Int,
): Parcelable




