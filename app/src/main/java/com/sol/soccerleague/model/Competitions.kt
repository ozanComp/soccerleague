package com.sol.soccerleague.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Competitions (
    val  teams: List<Team>? = null
): Parcelable