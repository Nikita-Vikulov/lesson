package com.example.lesson.data

import com.example.lesson.data.cache.RoomGithubUsersCache
import com.example.lesson.data.db.GithubDatabase
import com.example.lesson.remote.ApiHolder
import com.example.lesson.utils.INetworkStatus

class GithubUsersRepo (
    private val networkStatus: INetworkStatus,
    private val db: GithubDatabase,
    private val apiHolder: ApiHolder
) {
    fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
         val cacheUsers = RoomGithubUsersCache(db)
        if (isOnline) {
            apiHolder.apiService.getUsers("/users")
                .flatMap { users -> cacheUsers.sethUsersInDB(users)

                }
        } else {
            cacheUsers.getUsersOutDB()
        }
    }
}





