package com.example.lesson.remote

import com.example.lesson.data.GithubRepo
import com.example.lesson.data.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface GithubUsersService {

    @GET
    fun getRepo(@Url url:String): Single<List<GithubRepo>>

    @GET
    fun getUsers(@Url url:String): Single<List<GithubUser>>

    @GET
    fun getRepositories(@Url reposUrl: String): Single<List<GithubRepo>>
}