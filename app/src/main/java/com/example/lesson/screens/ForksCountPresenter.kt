package com.example.lesson.screens

import com.example.lesson.activity.MainView
import com.example.lesson.navigation.AndroidScreens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class ForksCountPresenter:MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    fun backPressed(): Boolean {
        router.backTo(AndroidScreens.UsersScreen())
        return true
    }
}