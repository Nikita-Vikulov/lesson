package com.example.lesson.presentation

import com.example.lesson.view.IItemView
import com.example.lesson.view.RepoItemView
import com.example.lesson.view.ui.ReposRVAdapter

interface IRListPresenter<V : IItemView> {

    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: ReposRVAdapter.ViewHolder)
    fun getCount(): Int
}

interface IRepoListPresenter : IRListPresenter<RepoItemView>
