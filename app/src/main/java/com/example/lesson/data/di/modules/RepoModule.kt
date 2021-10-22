package com.example.lesson.data.di.modules

import com.example.lesson.data.GithubRepoRepo
import com.example.lesson.data.cache.IRepositoriesCache
import com.example.lesson.data.cache.RoomGithubRepositoriesCache
import com.example.lesson.data.db.GithubDatabase
import com.example.lesson.data.scopes.RepositoryScope
import com.example.lesson.remote.IApiHolder
import com.example.lesson.utils.INetworkStatus
import dagger.Module
import dagger.Provides

@Module
open class RepoModule {

    @Provides
    @RepositoryScope
    fun reposCache(db: GithubDatabase): IRepositoriesCache {
        return RoomGithubRepositoriesCache(db)
    }

    @Provides
    @RepositoryScope
    fun githubRepo(
        networkStatus: INetworkStatus,
        cache: IRepositoriesCache,
        apiHolder: IApiHolder
    ): GithubRepoRepo {
        return GithubRepoRepo(
            networkStatus = networkStatus,
            cacheRepos = cache,
            apiHolder = apiHolder
        )
    }
}