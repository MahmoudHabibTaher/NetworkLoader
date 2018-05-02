package eu.parent.android.app.common.presentation.dialogs

import android.support.v7.app.AlertDialog
import eu.parent.android.app.R
import eu.parent.android.app.common.presentation.viewmodels.BaseViewModel

/**
 * Created by mahmoud on 1/15/18.
 */
class ConfirmDialog : BaseErrorDialog() {
    companion object {
        fun newInstance(errorMessage: String, onConfirmed: () -> Unit = {},
                        onCanceled: () -> Unit = {}) =
                ConfirmDialog().apply {
                    this.errorMessage = errorMessage
                    setOnConfirmListener(onConfirmed, onCanceled)
                }
    }

    override val baseViewModel: BaseViewModel?
        get() = null

    private var listener: ConfirmListener? = null

    fun setOnConfirmListener(onConfirmed: () -> Unit = {}, onCanceled: () -> Unit = {}) {
        listener = object : ConfirmListener {
            override fun onConfirm() {
                onConfirmed()
            }

            override fun onCancel() {
                onCanceled()
            }
        }
    }

    override fun setButtons(builder: AlertDialog.Builder) {
        builder.setPositiveButton(R.string.yes_text) { _, _ ->
            onConfirmClick()
        }

        builder.setNegativeButton(R.string.cancel_button) { _, _ ->
            onCancelClick()
        }
    }

    private fun onConfirmClick() {
        listener?.onConfirm()
    }

    private fun onCancelClick() {
        listener?.onCancel()
    }

    interface ConfirmListener {
        fun onConfirm()

        fun onCancel()
    }
}