package com.example.lesson.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.lesson.App
import com.example.lesson.databinding.LoginUserBinding
import com.example.lesson.model.GithubUser
import com.example.lesson.presentation.UserMainPresenter
import com.example.lesson.view.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserMainFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var binding: LoginUserBinding? = null

    private val presenter by moxyPresenter {
        UserMainPresenter(App.instance.router)
    }

    companion object {
        fun newInstance(fragment: GithubUser): UserMainFragment {
            return UserMainFragment().apply {
                arguments = bundleOf(KEY_ARG to fragment)
            }
        }

        private const val KEY_ARG = "USER_GIT"
    }

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

