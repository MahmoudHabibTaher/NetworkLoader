package eu.parent.android.app.common.presentation.dialogs

import android.support.v7.app.AlertDialog
import eu.parent.android.app.R
import eu.parent.android.app.common.presentation.viewmodels.BaseViewModel
import io.reactivex.subjects.PublishSubject


/**
 * Created by mahmoud on 9/12/17.
 */
class ConfirmActionDialog(confirmMessage: String = "") : BaseErrorDialog(confirmMessage) {

    override val baseViewModel: BaseViewModel?
        get() = null

    val onConfirmClickedObservable = PublishSubject.create<Boolean>()
    val onCancelClickedObservable = PublishSubject.create<Boolean>()

    var onConfirmClick: () -> Unit = {
        onConfirmClickedObservable.onNext(true)
    }
    var onCancelClick: () -> Unit = {
        onCancelClickedObservable.onNext(true)
    }


    override fun setButtons(builder: AlertDialog.Builder) {
        builder.setPositiveButton(R.string.yes_text) { _, _ ->
//            onConfirmClick
            onConfirmClickedObservable.onNext(true)
        }

        builder.setNegativeButton(R.string.cancel_button) { _, _ ->
            onCancelClickedObservable.onNext(true)
//            onCancelClick
        }
    }
}