package com.example.lesson.screens


import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.lesson.view.ui.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen


object AndroidScreens {

    class UsersScreen(private val fragment: Bundle) : SupportAppScreen() {

        override fun getFragment(): Fragment? = UsersFragment.newInstance(fragment)
    }

}