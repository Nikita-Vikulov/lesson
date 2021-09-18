package com.example.lesson

import android.os.Bundle
import com.example.lesson.presentation.UsersPresenter
import com.example.lesson.screens.AndroidScreens
import com.example.lesson.view.ui.UsersFragment
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class MainPresenter(private val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(AndroidScreens.UsersScreen())
    }

    fun backPressed() {
        router.exit()
    }
}