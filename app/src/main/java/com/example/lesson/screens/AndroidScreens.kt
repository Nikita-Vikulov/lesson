package com.example.lesson.screens

import androidx.fragment.app.Fragment
import com.example.lesson.model.GithubRepo
import com.example.lesson.model.GithubUser
import com.example.lesson.view.ui.ForksCountFragment
import com.example.lesson.view.ui.OnlyUserFragment
import com.example.lesson.view.ui.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen


object AndroidScreens {

    class UsersScreen : SupportAppScreen() {

        override fun getFragment() : Fragment = UsersFragment() //.newInstance(fragment)
    }

    class OnlyUserScreen(private val fragment: GithubUser) : SupportAppScreen() {

        override fun getFragment() = OnlyUserFragment.newInstance(fragment)
    }

    class ReposScreen(private val fragment: GithubRepo) : SupportAppScreen() {

        override fun getFragment() = ForksCountFragment.newInstance(fragment)
    }
}
