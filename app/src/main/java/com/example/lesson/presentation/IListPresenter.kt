package com.example.lesson.presentation

import com.example.lesson.view.IItemView
import com.example.lesson.view.UserItemView
import com.example.lesson.view.ui.UsersRVAdapter

interface IListPresenter<V : IItemView> {

    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: UsersRVAdapter.ViewHolder)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>
