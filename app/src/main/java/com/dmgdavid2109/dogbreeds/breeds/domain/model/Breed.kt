package com.dmgdavid2109.dogbreeds.breeds.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Breed(
    val name: String,
    val subBreed: String = ""
) : Parcelable {
    override fun toString(): String {
        return "$name $subBreed"
    }

    fun hasSubBread() = subBreed.isNullOrEmpty().not()
}
