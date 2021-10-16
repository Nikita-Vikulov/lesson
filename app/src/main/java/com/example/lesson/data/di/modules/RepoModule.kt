package com.example.lesson.data.di.modules

import com.example.lesson.data.GithubRepoRepo
import com.example.lesson.data.GithubUsersRepo
import com.example.lesson.data.db.GithubDatabase
import com.example.lesson.remote.ApiHolder
import com.example.lesson.remote.IApiHolder
import com.example.lesson.utils.INetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(
        networkStatus: INetworkStatus,
        db: GithubDatabase,
        apiHolder: ApiHolder
    ): GithubUsersRepo {
        return GithubUsersRepo(
            networkStatus = networkStatus,
            db = db,
            apiHolder = apiHolder
        )
    }

    @Singleton
    @Provides
    fun githubRepo(
        networkStatus: INetworkStatus,
        db: GithubDatabase,
        apiHolder: IApiHolder
    ): GithubRepoRepo {
        return GithubRepoRepo(
            networkStatus = networkStatus,
            db = db,
            apiHolder = apiHolder
        )
    }
}