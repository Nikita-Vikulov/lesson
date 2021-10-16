package com.example.lesson.items

import com.example.lesson.adapter.adapter.ReposRVAdapter

interface IRListPresenter<V : IItemView> {

    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: ReposRVAdapter.ViewHolder)
    fun getCount(): Int
}

interface IRepoListPresenter : IRListPresenter<RepoItemView>