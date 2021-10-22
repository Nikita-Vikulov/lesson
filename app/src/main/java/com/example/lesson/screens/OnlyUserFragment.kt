package com.example.lesson.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson.App
import com.example.lesson.data.GithubUser
import com.example.lesson.databinding.LoginUserBinding
import com.example.lesson.navigation.BackButtonListener
import com.example.lesson.screens.adapter.ReposRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class OnlyUserFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {


    private var binding: LoginUserBinding? = null

    private val presenter by moxyPresenter {
        App.instance.initRepositorySubcomponent()
        OnlyUserPresenter(user).apply {
            App.instance.repositorySubcomponent?.inject(this)
        }
    }
    companion object {
        private const val KEY_ARG = "USER_GIT"
        var urlRepos: String? = null
        lateinit var user: GithubUser

        fun newInstance(user: GithubUser): OnlyUserFragment {
            return OnlyUserFragment().apply {
                urlRepos = user.reposUrl
                this@Companion.user = user
                arguments = bundleOf(Pair(KEY_ARG, user))
            }
        }
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

