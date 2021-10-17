package com.example.lesson.items

import com.example.lesson.adapter.adapter.UserItemView
import com.example.lesson.adapter.adapter.UsersRVAdapter

interface IListPresenter<V : IItemView> {

    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: UsersRVAdapter.ViewHolder)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>
