package com.example.lesson.data

import com.example.lesson.data.cache.IRepositoriesCache
import com.example.lesson.remote.IApiHolder
import com.example.lesson.utils.INetworkStatus

class GithubRepoRepo(
    private val networkStatus: INetworkStatus,
    private val cacheRepos: IRepositoriesCache,
    private val apiHolder: IApiHolder
) {
    fun getRepo(user: GithubUser) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            apiHolder.apiService.getRepo(user.reposUrl.orEmpty()).flatMap { repo ->
                cacheRepos
                    .setReposInDB(user, repo)
                    .toSingleDefault(repo)

            }
        } else {
            cacheRepos.getReposOutDB(user)
        }
    }

}
