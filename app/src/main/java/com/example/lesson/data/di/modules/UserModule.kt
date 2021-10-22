package com.example.lesson.data.di.modules

import com.example.lesson.data.GithubUsersRepo
import com.example.lesson.data.db.GithubDatabase
import com.example.lesson.data.scopes.UserScope
import com.example.lesson.remote.ApiHolder
import com.example.lesson.utils.INetworkStatus
import dagger.Module
import dagger.Provides

@Module
open class UserModule {

    @Provides
    @UserScope
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
}