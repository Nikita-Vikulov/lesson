package com.example.lesson.adapter


import android.util.Log
import com.example.lesson.adapter.adapter.UserItemView
import com.example.lesson.adapter.adapter.UsersRVAdapter
import com.example.lesson.data.GithubRepositoriesRepo
import com.example.lesson.data.GithubUser
import com.example.lesson.data.GithubUsersRepo
import com.example.lesson.items.IUserListPresenter
import com.example.lesson.navigation.AndroidScreens
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UsersPresenter(
    private val usersRepo: GithubUsersRepo,
    private val repositoriesRepo: GithubRepositoriesRepo,
    private val router: Router
) : MvpPresenter<UsersView>() {

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

        val stringObserver = object : Observer<GithubUser> {
            var disposable: Disposable? = null

            override fun onComplete() {
                println("onComplete")
            }

            override fun onSubscribe(d: Disposable?) {
                disposable = d
                println("onSubscribe")
            }

            override fun onNext(s: GithubUser?) {
                // println("onNext: $s")
                if (s != null) {
                    users.add(s)
                }
            }

            override fun onError(e: Throwable?) {
                println("onError: ${e?.message}")
            }
        }

        usersRepo.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                loadRepos(it.first())
                it
            }
            .subscribe({ users ->
                usersListPresenter.users.addAll(users)
                viewState.updateList()
            }, {
                Log.e("UsersPresenter", "Ошибка получения", it)
            })
    }

    private fun loadRepos(user: GithubUser) {
        repositoriesRepo.getRepositories(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ repos ->
                Log.d("UsersPresenter", "${repos.first()}")
            }, {
                Log.e("UsersPresenter", "Ошибка получения пользователей", it)
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}









