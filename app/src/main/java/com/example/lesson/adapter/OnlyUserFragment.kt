package com.example.lesson.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson.App
import com.example.lesson.adapter.adapter.ReposRVAdapter
import com.example.lesson.data.GithubRepo
import com.example.lesson.data.GithubUser
import com.example.lesson.databinding.LoginUserBinding
import com.example.lesson.navigation.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class OnlyUserFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var binding: LoginUserBinding? = null

    private val presenter by moxyPresenter {
        OnlyUserPresenter(
            GithubRepo(),
            App.instance.router
        )
    }

    companion object {
        fun newInstance(fragment: GithubUser): OnlyUserFragment {
            return OnlyUserFragment().apply {
                arguments = bundleOf(KEY_ARG to fragment)
            }
        }

        private const val KEY_ARG = "USER_GIT"
    }

    private val adapter by lazy { ReposRVAdapter(presenter.reposListPresenter) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginUserBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gitUser = arguments?.getParcelable<GithubUser>("USER_GIT")
        binding?.loginUser?.text = gitUser?.login //+ " test userName"
    }


    override fun init() {
        binding?.rvRepo?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvRepo?.adapter = adapter
    }


    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }
}

