package com.example.lesson.adapter

import com.example.lesson.adapter.adapter.ReposRVAdapter
import com.example.lesson.data.GithubRepo
import com.example.lesson.data.GithubRepoRepo
import com.example.lesson.data.GithubUser
import com.example.lesson.items.IRepoListPresenter
import com.example.lesson.items.RepoItemView
import com.example.lesson.navigation.AndroidScreens
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class OnlyUserPresenter: MvpPresenter<UsersView>() {

    @Inject
    lateinit var githubRepo: GithubRepoRepo

    @Inject
    lateinit var router: Router


    class RepoListPresenter : IRepoListPresenter {

        val repos = mutableListOf<GithubRepo>()

        override var itemClickListener: ((RepoItemView) -> Unit)? = null

        override fun getCount(): Int = repos.size

        override fun bindView(view: ReposRVAdapter.ViewHolder) {
            val repo = repos[view.pos]
            view.showRepo(repo.name.orEmpty())
        }
    }

    val reposListPresenter = RepoListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData(OnlyUserFragment.user)

        reposListPresenter.itemClickListener = { itemView ->
            val screen = AndroidScreens.ReposScreen(reposListPresenter.repos[itemView.pos])
            router.navigateTo(screen)
        }
    }

    val repos: MutableList<GithubRepo> = mutableListOf()

    private fun loadData(user:GithubUser) {

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

        githubRepo.getRepo(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ repos ->
                reposListPresenter.repos.addAll(repos)
                viewState.updateList()
            }, {
               // Log.e("OnlyUserPresenter", "Ошибка получения репозитория", it)
            })


    }

    fun backPressed(): Boolean {
        router.backTo(AndroidScreens.UsersScreen())
        return true
    }

}