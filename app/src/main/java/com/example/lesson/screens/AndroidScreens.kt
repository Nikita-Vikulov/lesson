package com.example.lesson.screens

import com.example.lesson.model.GithubUser
import com.example.lesson.view.ui.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen


object AndroidScreens {

    class UsersScreen(private val s: String) : SupportAppScreen() {

        override fun getFragment() = UsersFragment.newInstance(s)
    }

}