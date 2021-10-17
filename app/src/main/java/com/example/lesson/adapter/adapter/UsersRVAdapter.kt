package com.example.lesson.adapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson.databinding.ImageUserBinding
import com.example.lesson.items.IUserListPresenter
import com.example.lesson.images.GlideImageLoader

class UsersRVAdapter(
    private val presenterUser: IUserListPresenter,
    private val imageLoader: GlideImageLoader
) : RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ImageUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenterUser.itemClickListener?.invoke(this) }

        }
    }

    override fun getItemCount(): Int = presenterUser.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenterUser.bindView(holder.apply { pos = position })
    }

    inner class ViewHolder(private val vb: ImageUserBinding) : RecyclerView.ViewHolder(vb.root),
        UserItemView {

        override var pos: Int = -1

        override fun showLogin(login: String) {
            vb.tvLogin.text = login
        }

        override fun loadAvatar(url: String) {

            imageLoader.loadTo(url, vb.avatarImageView)
        }
    }

}