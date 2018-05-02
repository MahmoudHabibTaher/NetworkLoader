package eu.parent.android.app.common.presentation.dialogs

import android.support.v7.app.AlertDialog
import eu.parent.android.app.R

/**
 * Created by mahmoud on 9/12/17.
 */
class ErrorDialog(errorMessage: String = "") : BaseErrorDialog(errorMessage) {


    var onCloseClick: () -> Unit = {}

    override fun setButtons(builder: AlertDialog.Builder) {
        builder.setPositiveButton(R.string.close_button) { _, _ ->
            onCloseClick()
        }
    }


}