package com.dan190.mear.mvp

import android.content.Context
import android.support.annotation.StringRes

/**
 * Created by Dan on 27/01/2018.
 */
interface BaseMvpView {
    fun getContext(): Context

    fun showError(error: String?)

    fun showError(@StringRes stringResId: Int)

    fun showMessage(@StringRes srtResId: Int)

    fun showMessage(message: String)
}