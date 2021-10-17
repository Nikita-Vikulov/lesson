package com.example.lesson.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RoomGithubUser::class, RoomGithubRepository::class],
    version = 1
)
abstract class GithubDatabase : RoomDatabase() {

    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao
}