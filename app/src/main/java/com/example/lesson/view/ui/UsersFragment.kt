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
import com.example.lesson.view.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var vb: FragmentUsersBinding? = null
    private var binding: ItemUserBinding? = null

    private val presenter by moxyPresenter { UsersPresenter(GithubUsersRepo(), App.instance.router) }

    private val adapter by lazy { UsersRVAdapter(presenter.usersListPresenter) }

    companion object{
        lateinit var arguments: Bundle

        fun newInstance(bundle: Bundle) : UsersFragment {
            val fragment = UsersFragment
            fragment.arguments = bundle
            return UsersFragment()
        }
    }

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
//        return FragmentUsersBinding.inflate(inflater, container, false).also {
//            vb = it
//        }.root
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemUserBinding.inflate(inflater, container, false)
        vb = FragmentUsersBinding.inflate(inflater, container, false)
        return vb?.root
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gitUser = arguments?.getParcelable<GithubUser>("USER_GIT")
        binding?.tvLogin?.text = gitUser?.login
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(requireContext())
        vb?.rvUsers?.adapter = adapter

//        Toast.makeText(
//            requireContext(),
//            requireArguments().getString(KEY_ARG),
//            Toast.LENGTH_SHORT
//        ).show()
    }


    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
        binding = null
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }

//    companion object{
//        fun newInstance(bundle: Bundle): UsersFragment {
//            return UsersFragment().apply {
//                arguments = bundleOf(USER_GIT to bundle) }
//        }
//        private const val USER_GIT = "USER_GIT"
//    }

}