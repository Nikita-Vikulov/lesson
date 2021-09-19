package com.example.lesson.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lesson.App
import com.example.lesson.MainPresenter
import com.example.lesson.databinding.LoginUserBinding
import com.example.lesson.model.GithubUser
import com.example.lesson.view.BackButtonListener
import com.example.lesson.view.ui.UsersView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class MainFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var binding: LoginUserBinding? = null

    private val presenter by moxyPresenter {
        MainPresenter(
            App.instance.router
        )
    }

  //  private val adapter by lazy { UsersRVAdapter(presenter.usersListPresenter) }

    companion object {
        lateinit var arguments: Bundle

        fun newInstance(bundle: Bundle): MainFragment {
            val fragment = MainFragment
            fragment.arguments = bundle
            return MainFragment()
        }
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
        binding?.loginUser?.text = gitUser?.login
    }

    override fun init() {
//        vb?.rvUsers?.layoutManager = LinearLayoutManager(requireContext())
//        vb?.rvUsers?.adapter = adapter

    }


    override fun updateList() {
   //     adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun backPressed():Boolean {
        return presenter.backPressed()
    }

}

