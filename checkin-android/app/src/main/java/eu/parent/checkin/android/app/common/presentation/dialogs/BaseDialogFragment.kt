package eu.parent.android.app.common.presentation.dialogs

import android.os.Bundle
import android.support.design.widget.BaseTransientBottomBar
import android.support.design.widget.Snackbar
import android.support.v4.app.DialogFragment
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.android.SupportFragmentInjector
import com.parent.entities.ValidationError
import eu.parent.android.app.common.presentation.errors.PresentationError
import eu.parent.android.app.common.presentation.extensions.showSnackbar
import eu.parent.android.app.common.presentation.interfaces.DisposablesHolder
import eu.parent.android.app.common.presentation.interfaces.addDisposable
import eu.parent.android.app.common.presentation.viewmodels.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by mahmoud on 11/2/17.
 */
abstract class BaseDialogFragment : DialogFragment(), SupportFragmentInjector, DisposablesHolder {
    override val injector: KodeinInjector = KodeinInjector()

    override val compositeDisposable = CompositeDisposable()

    abstract val baseViewModel: BaseViewModel?

    private val fullScreenLoadingDialog by lazy {
        FullScreenLoadingDialog()
    }

    private val snackbars = mutableMapOf<Snackbar, BaseTransientBottomBar.BaseCallback<Snackbar>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeInjector()
    }

    override fun onResume() {
        super.onResume()
        baseViewModel?.apply {
            subscribeConfirmLogout(this)
            subscribeConfirmRestart(this)
        }
    }

    override fun onDestroy() {
        destroyInjector()
        super.onDestroy()
    }

    protected fun subscribeLoadingVisible(viewModel: BaseViewModel) {
        addDisposable(viewModel.isLoadingVisibleObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (activity != null && isAdded) {
                        setLoadingIndicatorVisible(it)
                    }
                })
    }

    protected open fun setLoadingIndicatorVisible(visible: Boolean) {}

    protected open fun showFullScreenLoadingDialog() {
        if (!fullScreenLoadingDialog.isAdded) {
            fullScreenLoadingDialog.show(childFragmentManager, "full_screen_loading_dialog")
        }
    }

    protected open fun hideFullScreenLoadingDialog() {
        if (fullScreenLoadingDialog.isAdded) {
            fullScreenLoadingDialog.dismiss()
        }
    }

    protected open fun setFullScreenLoadingIndicatorVisible(visible: Boolean) {
        fullScreenLoadingDialog.setLoading(visible)

        if (visible) {
            showFullScreenLoadingDialog()
        }
    }

    protected open fun setFullScreenLoadingSuccess(message: String,
                                                   hideAfterDelay: Long = -1,
                                                   onHide: () -> Unit = {}) {

        fullScreenLoadingDialog.setSuccess(message)
        if (hideAfterDelay > -1) {
            fullScreenLoadingDialog.hideAfter(hideAfterDelay, onHide)
        }
    }

    protected open fun setFullScreenLoadingError(message: String, hideAfterDelay: Long = -1) {
        fullScreenLoadingDialog.setError(message)
        if (hideAfterDelay > -1) {
            fullScreenLoadingDialog.hideAfter(hideAfterDelay)
        }
    }

    protected fun subscribeLoadingError(viewModel: BaseViewModel) {
        addDisposable(viewModel.loadingErrorObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (activity != null && isAdded) {
                        onLoadingError(it)
                    }
                })
    }

    protected open fun onLoadingError(error: String) {}

    protected fun subscribeError(viewModel: BaseViewModel) {
        addDisposable(viewModel.errorObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (activity != null && isAdded) {
                        when (it.type) {
                            PresentationError.ERROR_TEXT -> showError(it.error)
                            PresentationError.ERROR_TEXT_RETRY -> showErrorWithRetry(it.error)
                            PresentationError.ERROR_TEXT_CONFIRM -> showErrorWithConfirm(it.error)

                        }
                    }
                })
    }

    protected open fun subscribeValidationErrors(viewModel: BaseViewModel) {
        addDisposable(viewModel.validationErrorsObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    onValidationErrors(it)
                })
    }

    protected open fun subscribeConfirmLogout(viewModel: BaseViewModel) {
        addDisposable(viewModel.confirmLogoutObservable.subscribe {
            errorDialog(it, {
                viewModel.logout(null)
            })
        })
    }

    protected open fun subscribeConfirmRestart(viewModel: BaseViewModel) {
        addDisposable(viewModel.confirmRestartObservable.subscribe {
            errorDialog(it, {
                viewModel.restart()
            })
        })
    }

    protected open fun showSnackbar(message: String, onDismiss: () -> Unit = {}) {
        val callback = object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                super.onDismissed(transientBottomBar, event)
                onDismiss()
            }
        }
        val snackbar = activity.showSnackbar(message).addCallback(callback)
        snackbars.put(snackbar, callback)
    }

    protected open fun showError(error: String) {}

    protected open fun showErrorWithRetry(error: String) {}

    protected open fun showErrorWithConfirm(error: String) {}

    protected open fun onValidationErrors(errors: List<ValidationError>) {}
}