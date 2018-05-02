package eu.parent.android.app.common.presentation.fragments

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.os.Bundle
import android.support.design.widget.BaseTransientBottomBar
import android.support.design.widget.Snackbar
import android.view.*
import com.github.salomonbrys.kodein.android.KodeinSupportFragment
import eu.parent.android.app.R
import eu.parent.android.app.common.navgiation.NavigationManager
import eu.parent.android.app.common.presentation.viewmodels.BaseViewModel
import eu.parent.android.app.common.presentation.errors.PresentationError
import eu.parent.android.app.common.presentation.actvities.BaseActivity
import eu.parent.android.app.common.presentation.dialogs.FullScreenLoadingDialog
import eu.parent.android.app.common.presentation.extensions.showSnackbar
import com.parent.entities.ValidationError
import eu.parent.android.app.common.presentation.dialogs.errorDialog
import eu.parent.android.app.common.presentation.interfaces.DisposablesHolder
import eu.parent.android.app.common.presentation.interfaces.addDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.AnkoLogger

/**
 * Created by mahmoud on 9/8/17.
 */
abstract class BaseFragment : KodeinSupportFragment(), LifecycleRegistryOwner, AnkoLogger, DisposablesHolder {
    companion object {
        const val DEFAULT_FULL_SCREEN_DIALOG_HIDE_AFTER_TIME = 1250L
    }

    val baseActivity: BaseActivity?
        get() = if (isAdded && activity is BaseActivity) activity as BaseActivity else null

    abstract val baseViewModel: BaseViewModel?

    val navigationManager: NavigationManager = NavigationManager()

    protected val NO_LAYOUT_RES_ID = Int.MIN_VALUE
    protected val NO_MENU_RES_ID = -1

    protected val baseActivty: BaseActivity? by lazy {
        if (activity is BaseActivity) activity as BaseActivity else null
    }

    private val fullScreenLoadingDialog by lazy {
        FullScreenLoadingDialog()
    }

    override val compositeDisposable = CompositeDisposable()

    private val lifecycleRegistry = LifecycleRegistry(this)

    private val snackbars = mutableMapOf<Snackbar, BaseTransientBottomBar.BaseCallback<Snackbar>>()

    override fun getLifecycle(): LifecycleRegistry = lifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNavigationManager(navigationManager)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            if (getLayoutResId() != NO_LAYOUT_RES_ID) inflater?.inflate(getLayoutResId(), container, false)
            else null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        if (hasOptionsMenu() && getMenuResId() != NO_MENU_RES_ID) {
            inflater?.inflate(getMenuResId(), menu)
            onOptionsMenuCreated(menu)
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onResume() {
        super.onResume()
        baseViewModel?.let {
            subscribeConfirmLogout(it)
            subscribeConfirmRestart(it)
        }
    }

    override fun onDestroy() {
        snackbars.forEach { entry ->
            val snackbar = entry.key
            val callback = entry.value
            snackbar.removeCallback(callback)
        }
        super.onDestroy()
    }

    protected open fun initNavigationManager(navigationManager: NavigationManager) {}

    protected open fun onOptionsMenuCreated(menu: Menu?) {}

    protected open fun initView(view: View?) {

    }

    protected abstract fun getLayoutResId(): Int

    protected open fun getMenuResId() = NO_MENU_RES_ID

    open fun refresh() {}

    protected fun isDualPane() =
            resources.getBoolean(R.bool.two_pane_layout)

    protected fun subscribeLoadingVisible(viewModel: BaseViewModel) {
        addDisposable(viewModel.isLoadingVisibleObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (activity != null && isAdded) {
                        setLoadingIndicatorVisible(it)
                    }
                })
    }

    protected fun subscribeFullScreenLoadingVisible(viewModel: BaseViewModel) {
        addDisposable(viewModel.isFullScreenLoadingVisibleObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (activity != null && isAdded) {
                        setFullScreenLoadingIndicatorVisible(it)
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

    protected open fun setFullScreenLoadingSuccess(message: String, hideAfterDelay: Long = -1, onHide: () -> Unit = {}) {
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

    protected fun subscribeEmpty(viewModel: BaseViewModel) {
        addDisposable(viewModel.isEmptyObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (activity != null && isAdded) {
                        onEmpty()
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
        addDisposable(viewModel.confirmLogoutObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    errorDialog(it, {
                        viewModel.logout(null)
                    })
                })
    }

    protected open fun subscribeConfirmRestart(viewModel: BaseViewModel) {
        addDisposable(viewModel.confirmRestartObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe {
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

    protected open fun onEmpty() {}

    protected open fun onValidationErrors(errors: List<ValidationError>) {}

}