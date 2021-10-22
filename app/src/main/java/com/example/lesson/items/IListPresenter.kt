package com.example.lesson.items

import com.example.lesson.screens.adapter.UserItemView
import com.example.lesson.screens.adapter.UsersRVAdapter

interface IListPresenter<V : IItemView> {

    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: UsersRVAdapter.ViewHolder)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>
