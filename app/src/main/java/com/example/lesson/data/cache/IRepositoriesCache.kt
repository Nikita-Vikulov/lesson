package com.example.lesson.data.cache

import com.example.lesson.data.GithubRepo
import com.example.lesson.data.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IRepositoriesCache {
    fun setReposInDB(user: GithubUser, repos: List<GithubRepo>): Completable
    fun getReposOutDB(user: GithubUser): Single<List<GithubRepo>>
}