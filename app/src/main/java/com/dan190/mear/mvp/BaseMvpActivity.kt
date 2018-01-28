package com.dan190.mear.mvp

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import io.reactivex.Observable
import rx.android.schedulers.AndroidSchedulers

/**
 * Created by Dan on 27/01/2018.
 */
abstract class BaseMvpActivity<in V: BaseMvpView, T: BaseMvpPresenter<V>> : AppCompatActivity(), BaseMvpView {

    protected abstract var presenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this as V)
    }

    override fun getContext(): Context = this

    override fun showError(error: String?) {
        runOnUiThread { Toast.makeText(this, error, Toast.LENGTH_LONG).show() }
    }

    override fun showError(stringResId: Int) {
        runOnUiThread { Toast.makeText(this, this.getString(stringResId), Toast.LENGTH_LONG).show() }

    }

    override fun showMessage(srtResId: Int) {
        runOnUiThread { Toast.makeText(this, this.getString(srtResId), Toast.LENGTH_LONG).show() }
    }

    override fun showMessage(message: String) {
        runOnUiThread { Toast.makeText(this, message, Toast.LENGTH_LONG).show() }
    }
}