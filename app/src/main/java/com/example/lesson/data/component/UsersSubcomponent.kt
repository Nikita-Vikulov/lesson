package com.example.lesson.data.component

import com.example.lesson.data.di.modules.UserModule
import com.example.lesson.data.scopes.UserScope
import com.example.lesson.screens.UsersPresenter
import dagger.Subcomponent

@UserScope
@Subcomponent(
    modules = [UserModule::class]
)
interface UsersSubcomponent {
    fun repositorySubcomponent(): RepositorySubcomponent
    fun inject(usersPresenter: UsersPresenter)
}