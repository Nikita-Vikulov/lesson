package com.example.lesson.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class GithubRepo(

    @Expose
    @SerializedName("kind")
    val kind: String? = null,

    @Expose
    val url: Long? = null,

    @Expose
    @SerializedName("Listing")
    val listing: String? = null,

    @Expose
    @SerializedName("data")
    val data: String? = null,

    ) : Parcelable {
    fun getRepo() = ApiHolder.apiService.getRepo("/r/aww/hot.json")
}
