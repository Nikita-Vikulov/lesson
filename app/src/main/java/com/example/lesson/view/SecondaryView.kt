package com.example.lesson.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface SecondaryView : MvpView {
    fun init()
    fun updateList()
}