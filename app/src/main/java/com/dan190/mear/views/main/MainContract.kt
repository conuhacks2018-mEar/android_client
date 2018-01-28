package com.dan190.mear.views.main

import com.dan190.mear.mvp.BaseMvpPresenter
import com.dan190.mear.mvp.BaseMvpView

/**
 * Created by Dan on 27/01/2018.
 */
interface MainContract {
    interface View : BaseMvpView {
//        fun showListOfAlarms()
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun testBackgroundUpdate()

        fun loadAllAlarms()
    }
}