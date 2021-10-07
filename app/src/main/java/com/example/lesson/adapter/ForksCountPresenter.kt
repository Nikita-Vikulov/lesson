package com.example.lesson.adapter

import com.example.lesson.activity.MainView
import com.example.lesson.navigation.AndroidScreens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class ForksCountPresenter(private val router: Router):MvpPresenter<MainView>() {
    fun backPressed(): Boolean {
        router.backTo(AndroidScreens.UsersScreen())
        return true
    }
}