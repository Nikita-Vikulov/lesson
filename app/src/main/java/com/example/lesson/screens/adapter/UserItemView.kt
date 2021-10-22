package com.example.lesson.screens.adapter

import com.example.lesson.items.IItemView


interface UserItemView : IItemView {

    fun showLogin(login: String)
    fun loadAvatar(url: String)
}