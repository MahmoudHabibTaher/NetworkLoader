package eu.parent.android.app.common.presentation.extensions

import android.app.Activity
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView
import eu.parent.android.app.R
import org.jetbrains.anko.act

/**
 * Created by mahmoud on 6/19/17.
 */


fun Activity.showSnackbar(message: String, backgroundColor: Int = R.color.primaryBackground): Snackbar {
    val snackbar: Snackbar = makeSnackBar(message, backgroundColor)
    val snackbarView = snackbar.view
    val textView = snackbarView.findViewById<View>(android.support.design.R.id.snackbar_text) as TextView
    textView.maxLines = 5
    snackbar.show()
    return snackbar
}

fun Activity.showSnackbarWithAction(message: String, actionMessage: String, onClick: () -> Unit,
                                    backgroundColor: Int = R.color.primaryBackground, actionTextColor: Int = R.color.blueRegular): Snackbar {
    val snackbar: Snackbar = makeSnackBar(message, backgroundColor)
    snackbar.setActionTextColor(ContextCompat.getColor(this, actionTextColor))
    snackbar.setAction(actionMessage, {
        onClick()
    })
    snackbar.show()
    return snackbar
}

private fun Activity.makeSnackBar(message: String, backgroundColor: Int): Snackbar {
    val duration: Int = Snackbar.LENGTH_SHORT
    val snackbar: Snackbar = Snackbar.make(this.act.window.decorView.findViewById(android.R.id.content), message, duration)
    val snackbarView: View = snackbar.view
    var snackBarTextView = snackbarView.findViewById<TextView>(android.support.design.R.id.snackbar_text)
    snackbarView.setBackgroundColor(ContextCompat.getColor(this, backgroundColor))

    return snackbar
}
