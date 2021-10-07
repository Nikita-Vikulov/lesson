package com.example.lesson.adapter.adapter

import moxy.MvpView

interface ReposView: MvpView {
    fun init()
    fun updateList()
}