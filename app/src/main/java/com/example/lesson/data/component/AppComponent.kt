package com.example.lesson.data.component

import com.example.lesson.activity.MainActivity
import com.example.lesson.activity.MainPresenter
import com.example.lesson.data.di.modules.ApiModule
import com.example.lesson.data.di.modules.AppModule
import com.example.lesson.data.di.modules.CacheModule
import com.example.lesson.data.di.modules.CiceroneModule
import com.example.lesson.screens.ForksCountPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
      //  RepoModule::class
    ]
)
interface AppComponent {

    fun presenter(): MainPresenter

    fun usersSubcomponent(): UsersSubcomponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(forksCountPresenter: ForksCountPresenter)

}