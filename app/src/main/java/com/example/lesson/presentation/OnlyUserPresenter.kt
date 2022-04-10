package com.example.lesson.presentation

import android.util.Log
import com.example.lesson.model.GithubRepo
import com.example.lesson.screens.AndroidScreens
import com.example.lesson.view.RepoItemView
import com.example.lesson.view.ui.ReposRVAdapter
import com.example.lesson.view.ui.UsersView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class OnlyUserPresenter(
    private val githubRepo: GithubRepo,
    private val router: Router
) : MvpPresenter<UsersView>() {

    class RepoListPresenter : IRepoListPresenter {

        val repos = mutableListOf<GithubRepo>()

        override var itemClickListener: ((RepoItemView) -> Unit)? = null

        override fun getCount(): Int = repos.size

        override fun bindView(view: ReposRVAdapter.ViewHolder) {
            val repo = repos[view.pos]
            view.showRepo(repo.listing.orEmpty())
        }
    }

    val reposListPresenter = RepoListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()

        reposListPresenter.itemClickListener = { itemView ->
            val screen = AndroidScreens.ReposScreen(reposListPresenter.repos[itemView.pos])
            router.navigateTo(screen)
        }
    }

    val repos: MutableList<GithubRepo> = mutableListOf()

    private fun loadData() {

        val stringObserver = object : Observer<GithubRepo> {
            var disposable: Disposable? = null

            override fun onComplete() {
                println("onComplete")
            }

            override fun onSubscribe(d: Disposable?) {
                disposable = d
                println("onSubscribe")
            }

            override fun onNext(s: GithubRepo?) {
                println("onNext: $s")
                if (s != null) {
                    repos.add(s)
                }
            }

            override fun onError(e: Throwable?) {
                println("onError: ${e?.message}")
            }
        }

        githubRepo.getRepo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ repos ->
                reposListPresenter.repos.addAll(repos)
                viewState.updateList()
            }, {
                Log.e("OnlyUserPresenter", "Ошибка получения репозитория", it)
            })


    }

    fun backPressed(): Boolean {
        router.backTo(AndroidScreens.UsersScreen())
        return true
    }

}