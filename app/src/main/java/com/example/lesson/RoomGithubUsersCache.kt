package com.example.lesson

import com.example.lesson.data.GithubUser
import com.example.lesson.data.db.GithubDatabase
import com.example.lesson.data.db.RoomGithubUser
import io.reactivex.rxjava3.core.Single

class RoomGithubUsersCache(private val db: GithubDatabase) : IUserCache {

    override fun sethUsersInDB(users: List<GithubUser>): Single<List<GithubUser>> =
        Single.fromCallable {
            val roomUsers = users.map { user ->
                RoomGithubUser(
                    user.id.toString(),
                    user.login.orEmpty(),
                    user.avatarUrl.orEmpty(),
                    user.reposUrl.orEmpty()
                )
            }
            db.userDao.insert(roomUsers)
            users
        }

    override fun getUsersOutDB(): Single<List<GithubUser>> =
        Single.fromCallable {
            db.userDao.getAll().map { roomUser ->
                GithubUser(
                    login = roomUser.login,
                    id = roomUser.id.toLong(),
                    avatarUrl = roomUser.avatarUrl,
                    reposUrl = roomUser.reposUrl,
                )
            }
        }
}