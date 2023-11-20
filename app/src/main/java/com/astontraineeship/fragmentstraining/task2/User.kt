package com.astontraineeship.fragmentstraining.task2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (
    val userId: Int,
    var firstName: String = "",
    var lastName: String = "",
    var phoneNumber: String = "",
    var photoUrl: String = ""
) : Parcelable

