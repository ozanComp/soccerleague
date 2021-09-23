package com.sol.soccerleague.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team (
    var id: Int,
    var name: String,
    var shortName: String,
    var tla: String,
): Parcelable