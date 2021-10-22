package com.example.lesson.data.component

import com.example.lesson.data.di.modules.RepoModule
import com.example.lesson.data.scopes.RepositoryScope
import com.example.lesson.screens.OnlyUserPresenter
import dagger.Subcomponent

@RepositoryScope
@Subcomponent(
    modules = [RepoModule::class]
)

interface RepositorySubcomponent {
    fun inject(reposPresenter: OnlyUserPresenter)
}