package eu.parent.android.app.user.auth.login.presentation

import android.app.Application
import com.github.salomonbrys.kodein.Kodein
import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.CompletableUseCase
import com.parent.domain.base.Params
import com.parent.domain.base.SingleUseCase
import com.parent.domain.common.validation.ValidationUtils
import com.parent.domain.user.SavePushNotificationToken
import com.parent.domain.user.UserLogin
import com.parent.entities.exceptions.AccountNotActiveException
import com.parent.entities.exceptions.InvalidLoginException
import com.parent.entities.exceptions.InvalidToken
import eu.parent.android.app.R
import eu.parent.android.app.common.presentation.errors.PresentationError
import eu.parent.android.app.common.presentation.viewmodels.KodeinBaseViewModel
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject

/**
 * Created by mahmoud on 9/8/17.
 */
class LoginViewModel(application: Application,
                     kodein: Kodein,
                     private val userLogin: CompletableUseCase,
                     private val getPushNotificationToken: BaseSingleUseCase<String>,
                     private val savePushNotificationToken: CompletableUseCase,
                     private val getUserLastEmail: SingleUseCase<String>) : KodeinBaseViewModel(application, kodein) {
    enum class InputField {
        EMAIL, PASSWORD
    }

    enum class NavigationEvent {
        HOME, ACCOUNT_ACTIVATION, FORGOT_PASSWORD
    }

    val validationErrorObservable = PublishSubject.create<Pair<InputField, String>>()

    val loginSuccessObservable = PublishSubject.create<Boolean>()

    val navigationEventObservable = PublishSubject.create<NavigationEvent>()
    val getUserLastEmailObservable = PublishSubject.create<String>()

    override fun start(args: Map<String, Any?>) {
        getLastEmail()
    }

    private fun getLastEmail() {
        getUserLastEmail.getSingle().subscribeBy(onSuccess = {
            getUserLastEmailObservable.onNext(it)
        })

    }

    fun login(email: String, password: String) {
        validateInput(email, password) {
            isLoadingVisible = true
            userLogin.getCompletable(Params(UserLogin.PARAM_EMAIL to email,
                    UserLogin.PARAM_PASSWORD to password))
                    .subscribeBy(onComplete = {
                        onLoginSuccess()
                        getPushNotificationUserId()
                    }, onError = {
                        isLoadingVisible = false
                        when (it) {
                            is AccountNotActiveException -> navigationEventObservable.onNext(NavigationEvent.ACCOUNT_ACTIVATION)
                            is InvalidLoginException -> notifyError(it.message ?:
                                    getString(R.string.unknown_server_error), PresentationError.ERROR_TEXT)
                            else -> notifyError(it.message ?:
                                    getString(R.string.unknown_server_error), PresentationError.ERROR_TEXT_RETRY)
                        }
                    })
        }
    }

    fun getPushNotificationUserId() {
        getPushNotificationToken.getSingle(Params("" to ""))
                .subscribeBy(onSuccess = {
                    SavePushNotificationUserId(it)
                })
    }


    fun SavePushNotificationUserId(userId: String) {
        isLoadingVisible = true
        savePushNotificationToken.getCompletable(Params(SavePushNotificationToken.PARAM_TOKEN to userId))
                .subscribeBy(onComplete = {
                    onLoginSuccess()
                }, onError = {
                    isLoadingVisible = false
                    when (it) {
                        is AccountNotActiveException -> navigationEventObservable.onNext(NavigationEvent.ACCOUNT_ACTIVATION)
                        is InvalidToken -> notifyError(it.message ?:
                                getString(R.string.unknown_server_error), PresentationError.ERROR_TEXT)
                        else -> notifyError(it.message ?:
                                getString(R.string.unknown_server_error), PresentationError.ERROR_TEXT_RETRY)
                    }
                })
    }


    fun forgotPasswordClick() {
        navigationEventObservable.onNext(NavigationEvent.FORGOT_PASSWORD)
    }

    private fun validateInput(email: String, password: String, onSuccess: () -> Unit) {
        var valid = true
        if (email.isBlank()) {
            validationErrorObservable.onNext(Pair(InputField.EMAIL, getString(R.string.required_input_validation_error)))
            valid = false
        } else if (!ValidationUtils.validateEmail(email)) {
            validationErrorObservable.onNext(Pair(InputField.EMAIL, getString(R.string.invalid_email_error)))
            valid = false
        }

        if (password.isBlank()) {
            validationErrorObservable.onNext(Pair(InputField.PASSWORD, getString(R.string.required_input_validation_error)))
            valid = false
        }

        if (valid) {
            onSuccess()
        }
    }

    private fun onLoginSuccess() {
        navigationEventObservable.onNext(NavigationEvent.HOME)
    }
}