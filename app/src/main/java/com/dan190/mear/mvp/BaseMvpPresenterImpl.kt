package com.dan190.mear.mvp

/**
 * Created by Dan on 27/01/2018.
 */
open class BaseMvpPresenterImpl<V: BaseMvpView> : BaseMvpPresenter<V> {
    protected var view: V? = null

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }
}