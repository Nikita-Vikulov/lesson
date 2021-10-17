package com.example.lesson.data

import com.example.lesson.RoomGithubRepositoriesCache
import com.example.lesson.data.db.GithubDatabase
import com.example.lesson.remote.ApiHolder
import com.example.lesson.utils.INetworkStatus
import javax.inject.Inject

class GithubRepositoriesRepo @Inject constructor(
    private val networkStatus: INetworkStatus,
    private val db: GithubDatabase,
    private val apiHolder: ApiHolder
) {
    fun getRepositories(user: GithubUser) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        val cacheRepos = RoomGithubRepositoriesCache(db, user)
        if (isOnline) {
            apiHolder.apiService.getRepositories(user.reposUrl.orEmpty()).flatMap { repositories ->
                cacheRepos.setReposInDB(repositories)
            }
        } else {
            cacheRepos.getReposOutDB()
        }
    }
}