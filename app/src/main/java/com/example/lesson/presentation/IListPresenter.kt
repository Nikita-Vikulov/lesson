package com.example.lesson.presentation

import com.example.lesson.view.IItemView
import com.example.lesson.view.UserItemView

interface IListPresenter<V : IItemView> {

    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>