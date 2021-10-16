package com.example.lesson.data.di.modules

import android.content.Context
import com.example.lesson.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: App) {


    @Provides
    fun app(): Context {
        return app
    }
}