package com.example.lesson

import android.os.Bundle
import com.example.lesson.screens.AndroidScreens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class MainPresenter(private val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(AndroidScreens.MainScreen(Bundle()))
    }

    fun backPressed() {
        router.exit()
    }
}
