package com.example.lesson.data.di.modules

import android.content.Context
import androidx.room.Room
import com.example.lesson.data.db.GithubDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun db(context: Context): GithubDatabase {
        return Room.databaseBuilder(context, GithubDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    companion object {

        private const val DB_NAME = "database.db"

    }
}