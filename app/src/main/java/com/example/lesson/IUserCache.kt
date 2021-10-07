package com.example.lesson

import com.example.lesson.data.GithubUser
import io.reactivex.rxjava3.core.Single

interface IUserCache {
    fun sethUsersInDB(users: List<GithubUser>): Single<List<GithubUser>>
    fun getUsersOutDB(): Single<List<GithubUser>>
}
