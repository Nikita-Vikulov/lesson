package com.example.lesson.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson.App
import com.example.lesson.databinding.FragmentUsersBinding
import com.example.lesson.databinding.ItemUserBinding
import com.example.lesson.model.GithubUser
import com.example.lesson.model.GithubUsersRepo
import com.example.lesson.presentation.UsersPresenter
import com.example.lesson.screens.MainFragment
import com.example.lesson.view.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var vb: FragmentUsersBinding? = null

    private val presenter by moxyPresenter { UsersPresenter(GithubUsersRepo(), App.instance.router) }

    private val adapter by lazy { UsersRVAdapter(presenter.usersListPresenter) }

    companion object {
       // lateinit var arguments: Bundle

        fun newInstance(bundle: Bundle): UsersFragment {
            val fragment = MainFragment
            fragment.arguments = bundle
            return UsersFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
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