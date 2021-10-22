package com.example.lesson

import android.app.Application
import com.example.lesson.data.component.AppComponent
import com.example.lesson.data.component.DaggerAppComponent
import com.example.lesson.data.component.RepositorySubcomponent
import com.example.lesson.data.component.UsersSubcomponent
import com.example.lesson.data.di.modules.AppModule

class App : Application() {

    lateinit var appComponent: AppComponent

    var usersSubcomponent: UsersSubcomponent? = null
    private set

    var repositorySubcomponent:RepositorySubcomponent? = null
    private set

    override fun onCreate() {
        super.onCreate()
        instance = this

            appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
    fun initUserSubcomponent() = appComponent.usersSubcomponent().also {
        usersSubcomponent = it
    }
    fun initRepositorySubcomponent() = appComponent.usersSubcomponent().repositorySubcomponent().also {
        repositorySubcomponent = it
    }
    fun releaseUserScope() {
        usersSubcomponent = null
    }
    fun releaseRepoScope() {
        repositorySubcomponent = null
    }


    companion object {

        lateinit var instance: App
    }
}
