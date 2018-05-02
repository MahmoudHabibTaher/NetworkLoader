package eu.parent.android.app.common.presentation.dialogs

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.widget.TextView
import eu.parent.android.app.R
import eu.parent.android.app.common.presentation.viewmodels.BaseViewModel

/**
 * Created by mahmoud on 9/12/17.
 */
abstract class BaseErrorDialog(protected var errorMessage: String = "") : BaseDialogFragment() {

    companion object {
        private const val KEY_STATE_ERROR_MSG = "error_msg"
    }

    override val baseViewModel: BaseViewModel?
        get() = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            errorMessage = savedInstanceState[KEY_STATE_ERROR_MSG] as String
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity, R.style.BaseAlertDialogTheme)
        setView(builder)
        setButtons(builder)
        return builder.create()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(KEY_STATE_ERROR_MSG, errorMessage)
        super.onSaveInstanceState(outState)
    }

    open protected fun setView(builder: AlertDialog.Builder) {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(getLayoutResId(), null)
        errorMessage = getMessage()
        view.findViewById<TextView>(R.id.error_text_view)?.text = errorMessage
        builder.setView(view)
    }

    open protected fun getLayoutResId(): Int = R.layout.layout_base_error_dialog

    open protected fun setButtons(builder: AlertDialog.Builder) {}

    open protected fun getMessage(): String = errorMessage
}