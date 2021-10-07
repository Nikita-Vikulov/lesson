package com.example.lesson

import com.example.lesson.data.GithubRepo
import com.example.lesson.data.GithubUser
import com.example.lesson.data.db.GithubDatabase
import com.example.lesson.data.db.RoomGithubRepository
import io.reactivex.rxjava3.core.Single

class RoomGithubRepositoriesCache(val db: GithubDatabase, val user: GithubUser) :
    IRepositoriesCache {

    override fun setReposInDB(repos: List<GithubRepo>): Single<List<GithubRepo>> =
        Single.fromCallable {
            val roomUser = db.userDao.getByLogin(user.login.orEmpty())
                ?: error("Такого пользователя нет")
            val roomRepos = repos.map {
                RoomGithubRepository(
                    id = it.id ?: "",
                    name = it.name ?: "",
                    forksCount = it.forksCount ?: 0,
                    userId = roomUser.id
                )
            }
            db.repositoryDao.insert(roomRepos)
            repos
        }

    override fun getReposOutDB(): Single<List<GithubRepo>> =
        Single.fromCallable {
            val roomUser = db.userDao.getByLogin(user.login.orEmpty())
                ?: error("Такого пользователя нет в БД")
            db.repositoryDao.getByUserId(roomUser.id).map {
                GithubRepo(
                    id = it.id,
                    name = it.name,
                    forksCount = it.forksCount
                )
            }
        }
}