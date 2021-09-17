package com.example.lesson.view.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson.App
import com.example.lesson.databinding.FragmentUsersBinding
import com.example.lesson.model.GithubUsersRepo
import com.example.lesson.presentation.UsersPresenter
import com.example.lesson.view.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var vb: FragmentUsersBinding? = null

    private val presenter by moxyPresenter { UsersPresenter(GithubUsersRepo(), App.instance.router) }

    private val adapter by lazy { UsersRVAdapter(presenter.usersListPresenter) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return FragmentUsersBinding.inflate(inflater, container, false).also {
            vb = it
        }.root
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(requireContext())
        vb?.rvUsers?.adapter = adapter

        Toast.makeText(
            requireContext(),
            requireArguments().getString(KEY_ARG),
            Toast.LENGTH_SHORT
        ).show()
    }

    @SuppressLint("NotifyDataSetChanged")
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
    companion object{
        fun newInstance(s: String): UsersFragment {
            return UsersFragment().apply {
                arguments = bundleOf(KEY_ARG to s) }
        }
        private const val KEY_ARG = "KET_ARG"
    }
}