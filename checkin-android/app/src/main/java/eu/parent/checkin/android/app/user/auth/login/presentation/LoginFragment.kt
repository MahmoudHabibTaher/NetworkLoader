package eu.parent.checkin.android.app.user.auth.login.presentation

import android.os.Bundle
import android.view.View
import com.github.salomonbrys.kodein.factory
import eu.parent.android.app.common.presentation.actvities.BaseActivity
import eu.parent.android.app.common.presentation.dialogs.ErrorDialog
import eu.parent.android.app.common.presentation.dialogs.RetryErrorDialog
import eu.parent.android.app.common.presentation.fragments.BaseFragment
import eu.parent.android.app.common.presentation.interfaces.addDisposable
import eu.parent.android.app.common.presentation.viewmodels.BaseViewModel
import eu.parent.checkin.android.app.user.auth.login.flow.LoginFlow
import eu.parent.checkin.android.app.user.auth.login.flow.LoginFlowController
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * Created by mahmoud on 9/8/17.
 */
class LoginFragment : BaseFragment() {
    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModelProvider: (BaseActivity) -> LoginViewModel by factory()

    private lateinit var viewModel: LoginViewModel
    override val baseViewModel: BaseViewModel?
        get() = viewModel
    private lateinit var loginFlow: LoginFlow
    private var email = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = viewModelProvider(activity as BaseActivity)
        loginFlow = LoginFlowController(activity as BaseActivity, R.id.login_fragment_container)
    }

    override fun getLayoutResId(): Int = R.layout.fragment_login

    override fun initView(view: View?) {
        super.initView(view)
        forgot_password_text_view.clicks().subscribe {
            viewModel.forgotPasswordClick()
        }

        login_button.clicks().subscribe {
            login()
        }

    }

    override fun onResume() {
        super.onResume()
        subscribeLoadingVisible(viewModel)
        subscribeLoadingError(viewModel)
        subscribeError(viewModel)
        subscribeValidationErrors()
        subscribeLoginSuccess()
        subscribeNavigationEvents()
        subscribeGetLastUserEmailObservable()
        viewModel.start()
    }

    private fun subscribeGetLastUserEmailObservable() {
        addDisposable(viewModel.getUserLastEmailObservable.subscribeBy(onNext = {
            email_edit_text.setText(it)
        }))    }

    private fun login() {
        email = email_edit_text.text.toString()
        viewModel.login(email, password_edit_text.text.toString())
    }

    private fun subscribeValidationErrors() {
        addDisposable(viewModel.validationErrorObservable.subscribeBy(onNext = {
            when (it.first) {
                LoginViewModel.InputField.EMAIL -> email_edit_text.error = it.second
                LoginViewModel.InputField.PASSWORD -> password_edit_text.error = it.second
            }
        }))
    }

    private fun subscribeLoginSuccess() {
        addDisposable(viewModel.loginSuccessObservable.subscribeBy(onNext = {
            showLoginSuccess()
        }))
    }

    private fun subscribeNavigationEvents() {
        addDisposable(viewModel.navigationEventObservable.subscribeBy(onNext = {
            when (it) {
                LoginViewModel.NavigationEvent.HOME -> {
                    loginFlow.showHomeScreen()
                }
            }
        }))
    }

    override fun setLoadingIndicatorVisible(visible: Boolean) {
        loading_indicator_view.setLoading(visible)
        login_form_layout.visible(!visible)
    }

    override fun showError(error: String) {
        ErrorDialog(error).show(childFragmentManager, "error_dialog")
    }

    override fun showErrorWithRetry(error: String) {
        RetryErrorDialog(error).apply {
            onRetryClick = {
                login()
            }
        }.show(childFragmentManager, "error_dialog")
    }

    private fun showLoginSuccess() {
        loading_indicator_view.setSuccess(getString(R.string.login_success_msg))
    }
}