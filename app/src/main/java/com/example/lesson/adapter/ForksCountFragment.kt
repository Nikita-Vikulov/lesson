package com.example.lesson.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.lesson.App
import com.example.lesson.databinding.GithubRepoBinding
import com.example.lesson.data.GithubRepo
import com.example.lesson.navigation.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ForksCountFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var binding: GithubRepoBinding? = null

    private val presenter by moxyPresenter {
        ForksCountPresenter(App.instance.router)

    }

    companion object {
        fun newInstance(fragment: GithubRepo): ForksCountFragment {
            return ForksCountFragment().apply {
                arguments = bundleOf(KEY_ARG to fragment)
            }
        }

        private const val KEY_ARG = "FORKS_COUNT"
    }

   // private val adapter by lazy { ReposRVAdapter(presenter.reposListPresenter) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GithubRepoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gitForks = arguments?.getParcelable<GithubRepo>("FORKS_COUNT")
        binding?.forksCount?.text = "forks count :" + gitForks?.forksCount //+ " test userName"
    }


    override fun init() {
    }


    override fun updateList() {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }
}