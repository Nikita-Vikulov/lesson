package com.example.lesson.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson.App
import com.example.lesson.adapter.adapter.UsersRVAdapter
import com.example.lesson.data.GithubRepositoriesRepo
import com.example.lesson.data.GithubUsersRepo
import com.example.lesson.data.db.GithubDatabase
import com.example.lesson.databinding.FragmentUsersBinding
import com.example.lesson.images.GlideImageLoader
import com.example.lesson.navigation.BackButtonListener
import com.example.lesson.utils.AndroidNetworkStatus
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var vb: FragmentUsersBinding? = null

    private val presenter by moxyPresenter {
        UsersPresenter(
            GithubUsersRepo(
                AndroidNetworkStatus(requireContext()),
                GithubDatabase.getInstance()
            ),

            GithubRepositoriesRepo(
                AndroidNetworkStatus(requireContext()),
                GithubDatabase.getInstance()
            ),
            App.instance.router
        )

    }

    private val adapter by lazy { UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUsersBinding.inflate(inflater, container, false).also {
            vb = it
        }.root
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(requireContext())
        vb?.rvUsers?.adapter = adapter

    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }

}