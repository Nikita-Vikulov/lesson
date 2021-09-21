package com.example.lesson.presentation

import com.example.lesson.MainView
import com.example.lesson.screens.AndroidScreens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserMainPresenter(private val router: Router) : MvpPresenter<MainView>() {

    fun backPressed(): Boolean {
        router.backTo(AndroidScreens.UsersScreen())
        return true
    }

}