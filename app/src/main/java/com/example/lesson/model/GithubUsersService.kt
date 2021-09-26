package com.example.lesson.model

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface GithubUsersService {

    @GET
    fun getRepo(@Url url:String): Single<List<GithubRepo>>

    @GET
    fun getUsers(@Url url:String): Single<List<GithubUser>>
}