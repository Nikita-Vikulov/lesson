package com.example.lesson.screens


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentFactory
import com.example.lesson.view.ui.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen


object AndroidScreens {

    class UsersScreen(private val fragment: Bundle) : SupportAppScreen() {

        override fun getFragment() = UsersFragment.newInstance(fragment)
    }

    class MainScreen(private val fragment: Bundle) : SupportAppScreen() {

        override fun getFragment() = UsersFragment.newInstance(fragment)
    }
}
