package com.dan190.mear.views.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.dan190.mear.R
import com.dan190.mear.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Dan on 27/01/2018.
 */

class MainActivity : BaseMvpActivity<MainContract.View, MainContract.Presenter>(),
MainContract.View{
    override var presenter: MainContract.Presenter = MainPresenter()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message?.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        presenter.testBackgroundUpdate()

        swipeRefresh.setOnRefreshListener { onSwipeRefresh() }
    }

    fun onSwipeRefresh() {
        presenter.loadAllAlarms()
    }
}
