package com.example.lesson

import android.os.Bundle
import com.example.lesson.databinding.ActivityMainBinding
import com.example.lesson.screens.BackButtonListener
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.android.support.SupportAppNavigator


class MainActivity : MvpAppCompatActivity(), MainView {

    private val navigator = SupportAppNavigator(this, R.id.container)

    private val presenter by moxyPresenter {
        MainPresenter(App.instance.router)
    }

    private var _vb: ActivityMainBinding? = null

    private val tag = null

    private val vb
        get() = _vb!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)
//
//        //Okhttp
//        val client = OkHttpClient.Builder().build()
//        val request = Request.Builder()
//            .url("https://api.github.com/users")
//            .build()
//        client.newCall(request).enqueue(object: Callback{
//            override fun onFailure(call: Call, e: IOException) {
//                Log.e(tag, "ошибка запроса", e)
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                response.body?.let {
//                    Log.d(tag, it.string())
//                }
//            }
//        })
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigationHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backPressed()
    }

}