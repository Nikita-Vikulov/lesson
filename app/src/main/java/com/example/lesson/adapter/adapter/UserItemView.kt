package com.example.lesson.adapter.adapter

import com.example.lesson.items.IItemView


interface UserItemView : IItemView {

    fun showLogin(login: String)
    fun loadAvatar(url: String)
}