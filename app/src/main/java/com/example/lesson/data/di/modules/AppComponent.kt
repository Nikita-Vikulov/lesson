package com.example.lesson.data.di.modules

import com.example.lesson.activity.MainActivity
import com.example.lesson.activity.MainPresenter
import com.example.lesson.adapter.ForksCountPresenter
import com.example.lesson.adapter.OnlyUserPresenter
import com.example.lesson.adapter.UsersPresenter
import com.example.lesson.data.di.modules.CacheModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
        RepoModule::class
    ]
)
interface AppComponent {

   // fun presenter(): MainPresenter


    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(onlyUserPresenter: OnlyUserPresenter)
    fun inject(forksCountPresenter: ForksCountPresenter)
    fun inject(usersPresenter: UsersPresenter)

}