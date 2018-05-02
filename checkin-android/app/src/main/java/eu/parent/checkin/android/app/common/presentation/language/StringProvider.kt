package eu.parent.android.app.common.presentation.language

import android.annotation.SuppressLint
import android.content.Context
import android.support.annotation.StringRes

@SuppressLint("StaticFieldLeak")
object StringProvider {
    private var context: Context? = null

    fun init(context: Context) {
        this.context = context
    }

    fun getString(@StringRes stringResId: Int) =
            context?.getString(stringResId) ?: ""

    fun getString(@StringRes stringResId: Int, vararg formatArgs: String) =
            context?.getString(stringResId, formatArgs) ?: ""
}