package eu.parent.android.app.common.presentation.actvities

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.content.Context
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.github.salomonbrys.kodein.android.KodeinAppCompatActivity
import com.parent.domain.common.prefs.PrefsManager
import eu.parent.android.app.R
import eu.parent.android.app.common.navgiation.NavigationManager
import eu.parent.android.app.common.presentation.dialogs.errorDialog
import eu.parent.android.app.common.presentation.errors.PresentationError
import eu.parent.android.app.common.presentation.interfaces.DisposablesHolder
import eu.parent.android.app.common.presentation.interfaces.addDisposable
import eu.parent.android.app.common.presentation.viewmodels.BaseViewModel
import io.reactivex.disposables.CompositeDisposable
import java.util.*


/**
 * This is the parent Activity for all the Activities across the application
 *
 * Created by mahmoud on 9/8/17.
 */
abstract class BaseActivity : KodeinAppCompatActivity(), LifecycleRegistryOwner, DisposablesHolder {
    abstract val baseViewModel: BaseViewModel?

    var navigationManager: NavigationManager? = null
    var mainFragmentContainerId = R.id.main_fragment_container

    protected var toolbar: Toolbar? = null

    protected var isBackEnabled = false
        set(value) {
            field = value
            updateActionBar(supportActionBar)
        }

    protected var isBackButton = true
        set(value) {
            field = value
            updateActionBar(supportActionBar)
        }

    protected var hideBackOnRoot = true

    protected var isLogoEnabled = false
        set(value) {
            field = value
            updateActionBar(supportActionBar)
        }

    private val lifecycleRegistry = LifecycleRegistry(this)

    override val compositeDisposable = CompositeDisposable()

    override fun getLifecycle(): LifecycleRegistry = lifecycleRegistry


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        navigationManager = NavigationManager().apply {
            init(supportFragmentManager, mainFragmentContainerId)
        }

        navigationManager?.setOnBackStackChangedListener(object : NavigationManager.NavigationListener {
            override fun onBackStackChanged() {
                if (hideBackOnRoot) {
                    isBackEnabled = (navigationManager?.backStackCount() ?: 0 > 1)
                }
            }
        })

    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)

        initToolbar()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =
            when (item?.itemId) {
                android.R.id.home -> {
                    if (!onUpClick()) {
                        onBackPressed()
                    }
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }

    override fun onResume() {
        super.onResume()
        baseViewModel?.let {
            subscribeConfirmLogout(it)
            subscribeConfirmRestart(it)
        }
    }

    override fun attachBaseContext(newBase: Context) {
        val prefsManager = PrefsManager(newBase)
        val languageType = Locale(prefsManager.language.toLowerCase())
        super.attachBaseContext(AppContextWrapper.wrap(newBase, languageType))
    }

    private fun initToolbar() {
        toolbar = findViewById(R.id.toolbar)

        toolbar?.apply {
            title = ""
            navigationIcon = ContextCompat.getDrawable(context, R.drawable.ic_navigation_back)
        }

        setSupportActionBar(toolbar)

        updateActionBar(supportActionBar)
    }

    protected fun updateActionBar(actionBar: ActionBar?) {
        actionBar?.apply {
            setDisplayHomeAsUpEnabled(isBackEnabled)
        }

        toolbar?.apply {
            logo = if (isLogoEnabled) ContextCompat.getDrawable(context, R.drawable.ic_logo_horizontal_light)
            else null
            if (isBackEnabled) {
                if (isBackButton) {
                    toolbar?.setNavigationIcon(R.drawable.ic_navigation_back)
                } else {
                    toolbar?.setNavigationIcon(R.drawable.ic_close_nav)
                }
            }
        }
    }

    protected fun setToolbarTitle(@StringRes titleResId: Int) {
        setToolbarTitle(getString(titleResId))
    }

    protected fun setToolbarTitle(title: String) {
        toolbar?.title = title
    }

    override fun onBackPressed() {
        navigationBack()
    }

    protected fun navigationBack() {
        navigationManager?.navigateBackStack(this)
    }

    open protected fun onUpClick(): Boolean = false

    protected fun subscribeError(viewModel: BaseViewModel) {
        addDisposable(viewModel.errorObservable.subscribe {
            when (it.type) {
                PresentationError.ERROR_TEXT -> showError(it.error)
                PresentationError.ERROR_TEXT_RETRY -> showErrorWithRetry(it.error)
                PresentationError.ERROR_TEXT_CONFIRM -> showErrorWithConfirm(it.error)

            }
        })
    }

    open fun subscribeConfirmLogout(viewModel: BaseViewModel) {
        addDisposable(viewModel.confirmLogoutObservable.subscribe {
            errorDialog(it) {
                viewModel.logout(null)
            }
        })
    }

    open fun subscribeConfirmRestart(viewModel: BaseViewModel) {
        addDisposable(viewModel.confirmRestartObservable.subscribe {
            errorDialog(it) {
                viewModel.restart()
            }
        })
    }

    protected open fun showError(error: String) {
        // Template method
    }

    protected open fun showErrorWithRetry(error: String) {
        // Template method
    }

    protected open fun showErrorWithConfirm(error: String) {
        // Template method
    }


}