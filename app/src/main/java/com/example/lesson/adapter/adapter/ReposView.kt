package com.example.lesson.adapter.adapter

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface ReposView: MvpView {
    fun init()
    fun updateList()
}