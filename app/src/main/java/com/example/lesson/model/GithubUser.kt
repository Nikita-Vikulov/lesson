package com.example.lesson.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class GithubUser(
    val login: String
) : Parcelable
