package com.example.lesson.screens


import android.util.Log
import com.example.lesson.App
import com.example.lesson.data.GithubUser
import com.example.lesson.data.GithubUsersRepo
import com.example.lesson.items.IUserListPresenter
import com.example.lesson.navigation.AndroidScreens
import com.example.lesson.screens.adapter.UserItemView
import com.example.lesson.screens.adapter.UsersRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UsersPresenter : MvpPresenter<UsersView>() {

    @Inject
    lateinit var usersRepo: GithubUsersRepo

    @Inject
    lateinit var router: Router

    class UsersListPresenter : IUserListPresenter {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount(): Int = users.size

        override fun bindView(view: UsersRVAdapter.ViewHolder) {
            val user = users[view.pos]
            view.showLogin(user.login.orEmpty())
            view.loadAvatar(user.avatarUrl.orEmpty())
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val screen = AndroidScreens.OnlyUserScreen(usersListPresenter.users[itemView.pos])
            router.navigateTo(screen)
        }
    }

    val users: MutableList<GithubUser> = mutableListOf()

    private fun loadData() {

        usersRepo.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ users ->
                usersListPresenter.users.addAll(users)
                viewState.updateList()
            }, {
                Log.e("UsersPresenter", "Ошибка получения", it)
            })
    }


    fun backPressed(): Boolean {
        router.exit()
        return true
    }
    override fun onDestroy() {
        super.onDestroy()
        App.instance.releaseUserScope()
    }
}









