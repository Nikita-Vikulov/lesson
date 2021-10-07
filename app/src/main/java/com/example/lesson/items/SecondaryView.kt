package com.example.lesson.items

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface SecondaryView : MvpView {
    fun init()
    fun updateList()
}