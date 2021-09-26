package com.example.lesson.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class GithubRepo(

    @Expose
    @SerializedName("login")
    val login: String? = null,

    @Expose
    val id: Long? = null,

    @Expose
    @SerializedName("name")
    val name: String? = null,

    @Expose
    @SerializedName("forks")
    val forksCount: String? = null,

) : Parcelable {
    fun getRepo() = ApiHolder.apiService.getRepo("/users/${login}/repos")
}
