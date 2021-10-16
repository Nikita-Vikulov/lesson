package com.example.lesson.data

import com.example.lesson.RoomGithubRepositoriesCache
import com.example.lesson.data.db.GithubDatabase
import com.example.lesson.remote.IApiHolder
import com.example.lesson.utils.INetworkStatus

class GithubRepoRepo(
    private val networkStatus: INetworkStatus,
    val db: GithubDatabase,
    private val apiHolder: IApiHolder
) {

    fun getRepo(user: GithubUser) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        val cacheRepos = RoomGithubRepositoriesCache(db, user)

        if (isOnline) {
            apiHolder.apiService.getRepo(user.reposUrl.orEmpty()).flatMap { repo ->
                cacheRepos.setReposInDB(repo)
            }
        } else {
            cacheRepos.getReposOutDB()
        }
    }

}
