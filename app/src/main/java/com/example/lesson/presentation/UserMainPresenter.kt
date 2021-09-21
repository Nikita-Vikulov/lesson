package com.example.lesson.presentation

import android.os.Bundle
import com.example.lesson.MainView
import com.example.lesson.screens.AndroidScreens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserMainPresenter(private val router: Router) : MvpPresenter<MainView>() {

//    override fun onFirstViewAttach() {
//        super.onFirstViewAttach()
//        router.replaceScreen(AndroidScreens.UsersScreen())
//    }

    fun backPressed(): Boolean {
        router.backTo(AndroidScreens.UsersScreen())
        return true
    }

}