package com.example.lesson.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
    data class GithubRepo(
    @Expose
    @SerializedName("login")
    val login: String? = null,

    @Expose
    val id: String? = null,

    @Expose
    @SerializedName("name")
    val name: String? = null,

    @Expose
    @SerializedName("forks")
    val forksCount: Int? = null

) : Parcelable {

}
