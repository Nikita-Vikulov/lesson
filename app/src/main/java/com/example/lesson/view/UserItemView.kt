package com.example.lesson.view


interface UserItemView : IItemView {

    fun showLogin(login: String)
    fun loadAvatar(url: String)
}