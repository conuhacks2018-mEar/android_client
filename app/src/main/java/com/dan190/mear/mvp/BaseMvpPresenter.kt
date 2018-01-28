package com.dan190.mear.mvp

/**
 * Created by Dan on 27/01/2018.
 */
interface BaseMvpPresenter<in V: BaseMvpView> {

    fun attachView(view: V)

    fun detachView()
}