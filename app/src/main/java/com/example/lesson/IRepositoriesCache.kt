package com.example.lesson

import com.example.lesson.data.GithubRepo
import io.reactivex.rxjava3.core.Single

interface IRepositoriesCache {
    fun setReposInDB(repos: List<GithubRepo>): Single<List<GithubRepo>>
    fun getReposOutDB(): Single<List<GithubRepo>>
}