package com.example.lesson.screens



import com.example.lesson.model.GithubUser
import com.example.lesson.presentation.UsersPresenter.Companion.newInstance
import com.example.lesson.view.ui.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen
import java.lang.reflect.Array.newInstance


object AndroidScreens {

    class UsersScreen(private val s: GithubUser) : SupportAppScreen() {

        override fun getFragment() = UsersFragment.newInstance(s)
    }

}