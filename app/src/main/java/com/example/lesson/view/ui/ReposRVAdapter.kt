package com.example.lesson.view.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson.databinding.ItemRepoBinding
import com.example.lesson.presentation.IRepoListPresenter
import com.example.lesson.view.RepoItemView

class ReposRVAdapter(
    private val presenterRepo: IRepoListPresenter
) : RecyclerView.Adapter<ReposRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenterRepo.itemClickListener?.invoke(this) }
        }
    }

    override fun getItemCount(): Int = presenterRepo.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenterRepo.bindView(holder.apply { pos = position })
    }

    inner class ViewHolder(private val vb: ItemRepoBinding) : RecyclerView.ViewHolder(vb.root),
        RepoItemView {

        override var pos: Int = -1

        override fun showRepo(repo: String) {
            vb.tvRepo.text = repo
        }
    }

}
