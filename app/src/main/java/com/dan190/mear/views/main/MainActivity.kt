package com.dan190.mear.views.main

import android.app.LoaderManager
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.View
import com.dan190.mear.R
import com.dan190.mear.data.Alarm
import com.dan190.mear.data.contentProvider.AlarmColumns
import com.dan190.mear.data.contentProvider.AlarmProviderJava
import com.dan190.mear.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

/**
 * Created by Dan on 27/01/2018.
 */

class MainActivity :
        BaseMvpActivity<MainContract.View, MainContract.Presenter>(),
        MainContract.View,
        LoaderManager.LoaderCallbacks<Cursor> {

    private val PROJECTION = arrayOf(
            AlarmColumns.COLUMN_ID,
            AlarmColumns.COLUMN_TITLE,
            AlarmColumns.COLUMN_MESSAGE,
            AlarmColumns.COLUMN_DATE
    )
    override var presenter: MainContract.Presenter = MainPresenter()
    private val LOADER_ID_MESSAGES = 0
    private var cursor : Cursor? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                viewHome()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                viewSettings()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                viewNotifications()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewNotifications()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        presenter.testBackgroundUpdate()

        swipeRefresh.setOnRefreshListener { onSwipeRefresh() }

        loaderManager.initLoader(LOADER_ID_MESSAGES, null, this)
    }

    private fun viewNotifications() {
        // make notifications view visible
        notificationsCL?.visibility = View.VISIBLE

        // make the rest of the views gone
        homeCL?.visibility = View.GONE
        settingsCL?.visibility = View.GONE
    }

    private fun viewHome() {
        // make home visible
        homeCL?.visibility = View.VISIBLE

        // make everything else gone
        notificationsCL?.visibility = View.GONE
        settingsCL?.visibility = View.GONE
    }

    private fun viewSettings() {
        // make settings visible
        settingsCL?.visibility = View.GONE

        // make everything else gone
        notificationsCL?.visibility = View.GONE
        homeCL?.visibility = View.GONE
    }
    private fun onSwipeRefresh() {
        presenter.loadAllAlarms(cursor)
    }

    override fun showListOfAlarms(alarmList: List<Alarm>) {
        runOnUiThread {
            homePlaceholderview?.removeAllViews()
            for (alarm in alarmList) {
                val placeHolderView = AlarmAdapter(this, homePlaceholderview, alarm)
                homePlaceholderview?.addView(placeHolderView)
            }
            hideLoading()
        }
    }
    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        Timber.d("oncreateloader")
        return CursorLoader(this,
                AlarmProviderJava.AlarmMessages.CONTENT_URI,
                PROJECTION,
                null,
                null,
                AlarmColumns.COLUMN_DATE + " DESC"
        )
    }

    override fun onLoadFinished(loader: Loader<Cursor>?, data: Cursor?) {
        this.cursor = data
        presenter.loadAllAlarms(data)
    }

    override fun onLoaderReset(loader: Loader<Cursor>?) {
        Timber.w("Cursor loader is reset")
    }

    override fun hideLoading() {
        runOnUiThread {
            swipeRefresh?.isRefreshing = false
            progressBar?.visibility = View.GONE
            viewsCL?.alpha = 1F
        }
    }

    override fun showLoading() {
        runOnUiThread {
            swipeRefresh?.isRefreshing = false
            viewsCL?.alpha = 0.3F
            progressBar?.visibility = View.VISIBLE
        }
    }
}
