package com.example.lesson.screens

import androidx.fragment.app.Fragment
import com.example.lesson.presentation.UserMainPresenter
import com.example.lesson.view.ui.UserMainFragment
//import com.example.lesson.MainPresenter.Companion.newInstance
//import com.example.lesson.view.ui.MainPresenter
import com.example.lesson.view.ui.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen


object AndroidScreens {

    class UsersScreen : SupportAppScreen() {

        override fun getFragment() : Fragment = UsersFragment() //.newInstance(fragment)
    }

    class MainScreen(private val fragment: String) : SupportAppScreen() {

        override fun getFragment(): Fragment = UserMainFragment.newInstance(fragment)
    }
}
