package com.example.lesson

import android.app.Application
import com.example.lesson.data.di.modules.AppComponent
import com.example.lesson.data.di.modules.AppModule
import com.example.lesson.data.di.modules.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

            appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    companion object {

        lateinit var instance: App
    }
}
