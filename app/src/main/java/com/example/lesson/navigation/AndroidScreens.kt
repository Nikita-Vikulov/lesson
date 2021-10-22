package com.example.lesson.navigation

import androidx.fragment.app.Fragment
import com.example.lesson.data.GithubRepo
import com.example.lesson.data.GithubUser
import com.example.lesson.screens.ForksCountFragment
import com.example.lesson.screens.OnlyUserFragment
import com.example.lesson.screens.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen


object AndroidScreens {

    class UsersScreen : SupportAppScreen() {

        override fun getFragment() : Fragment = UsersFragment()
    }

    class OnlyUserScreen(private val fragment: GithubUser) : SupportAppScreen() {

        override fun getFragment() = OnlyUserFragment.newInstance(fragment)
    }

    class ReposScreen(private val fragment: GithubRepo) : SupportAppScreen() {

        override fun getFragment() = ForksCountFragment.newInstance(fragment)
    }
}
