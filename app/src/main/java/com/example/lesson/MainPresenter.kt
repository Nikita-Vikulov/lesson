package com.example.lesson

import com.example.lesson.model.GithubUser
import com.example.lesson.screens.AndroidScreens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class MainPresenter(private val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(AndroidScreens.UsersScreen(GithubUser()))
    }

    fun backPressed() {
        router.exit()
    }
}