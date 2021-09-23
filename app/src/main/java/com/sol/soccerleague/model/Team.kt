package com.sol.soccerleague.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team (
    var id: Int? = 0,
    var name: String? = null,
    var shortName: String? = null,
    var tla: String? = null,
): Parcelable