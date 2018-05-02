package eu.parent.android.app.common.presentation.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.support.annotation.StringRes
import com.parent.domain.auth.ISessionManager
import com.parent.domain.common.observers.DefaultCompletableObserver
import com.parent.domain.common.observers.DefaultMayeObserver
import com.parent.domain.common.observers.DefaultObserver
import com.parent.domain.common.observers.DefaultSingleObserver
import com.parent.domain.common.presentation.ErrorHandlerView
import com.parent.domain.common.presentation.GenericErrorHandlerView
import com.parent.domain.common.presentation.GenericErrors
import com.parent.domain.permissions.IPermissionsManager
import com.parent.entities.InstitutionModel
import com.parent.entities.ValidationError
import com.parent.entities.exceptions.TokenAlreadyRefreshedException
import com.parent.entities.exceptions.TokenExpiredException
import eu.parent.android.app.DispatchActivity
import eu.parent.android.app.R
import eu.parent.android.app.common.presentation.errors.PresentationError
import eu.parent.android.app.common.presentation.language.StringProvider
import eu.parent.android.app.main.presentation.MainActivity
import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import kotlin.properties.Delegates

/**
 * Created by mahmoud on 9/8/17.
 */
abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        const val SUCCESS_MSG_DELAY_MILLIS = 1500L
    }

    val isLoadingVisibleObservable: PublishSubject<Boolean> = PublishSubject.create()
    val isFullScreenLoadingVisibleObservable: PublishSubject<Boolean> = PublishSubject.create()
    val isEmptyObservable: PublishSubject<Boolean> = PublishSubject.create()
    val loadingErrorObservable: PublishSubject<String> = PublishSubject.create()
    val errorObservable: PublishSubject<PresentationError> = PublishSubject.create()
    val validationErrorsObservable: PublishSubject<List<ValidationError>> = PublishSubject.create()
    val confirmLogoutObservable: PublishSubject<String> = PublishSubject.create()
    val confirmRestartObservable: PublishSubject<String> = PublishSubject.create()

    abstract val sessionManager: ISessionManager

    abstract val permissionsManager: IPermissionsManager

    private val errorHandler: ErrorHandlerView? by lazy {
        GenericErrorHandlerView(onError = { code, message ->
            onGenericError(code, message)
        })
    }

    private val defaultOnLogout: (String?) -> Unit = { message ->
        if (message?.isNotBlank() == true) {
            confirmLogoutObservable.onNext(message)
        } else {
            val app = getApplication<Application>()
            app.startActivity(app.intentFor<DispatchActivity>().clearTask().newTask())
        }
    }

    private val defaultOnPermissionsChanged: (String?) -> Unit = { message ->
        if (message?.isNotBlank() == true) {
            confirmRestartObservable.onNext(message)
        } else {
            restart()
        }
    }

    open protected var isLoadingVisible: Boolean by Delegates.observable(false) { _, _, newValue ->
        isLoadingVisibleObservable.onNext(newValue)
    }

    open protected var isFullScreenLoadingVisible: Boolean by Delegates.observable(false) { _, _, newValue ->
        isFullScreenLoadingVisibleObservable.onNext(newValue)
    }

    open protected var isEmpty: Boolean by Delegates.observable(false) { _, _, newValue ->
        isEmptyObservable.onNext(newValue)
    }

    protected fun getString(@StringRes stringResId: Int) =
            StringProvider.getString(stringResId)

    protected fun isDualPane() =
            getApplication<Application>().resources.getBoolean(R.bool.two_pane_layout)

    protected fun getErrorMessage(throwable: Throwable): String =
            throwable.message?.takeIf { it.isNotBlank() }
                    ?: getString(R.string.unknown_server_error)

    protected fun onError(throwable: Throwable, handleError: (Throwable) -> Unit = {}) {
        if (errorHandler?.handleError(throwable) == false) {
            handleError(throwable)
        }
    }

    protected fun notifyError(error: String, type: Int) {
        errorObservable.onNext(PresentationError(error, type))
    }

    protected fun notifyValidationErrors(errors: List<ValidationError>) {
        validationErrorsObservable.onNext(errors)
    }

    protected fun isPermissionsGranted(permissions: List<String>, onResult: (Map<String, Boolean>) -> Unit) {
        permissionsManager.isPermissionsGranted(permissions)
                .subscribeBy(onSuccess = {
                    onResult(it)
                })
    }

    protected fun isCompanyAdmin(onResult: (Boolean) -> Unit) {
        sessionManager.isCompanyAdmin().subscribeBy(onSuccess = {
            onResult(it)
        })
    }

    protected fun <T> subscribeObservable(observable: Observable<T>,
                                          onNext: (T) -> Unit = {},
                                          onComplete: () -> Unit = {},
                                          onError: (Throwable) -> Unit = {}): Disposable =
            observable.retryWhen { errors ->
                errors.flatMap {
                    when (it) {
                        is TokenExpiredException -> onTokenExpiredError()
                                .andThen(Observable.fromCallable { true })
                        is TokenAlreadyRefreshedException ->
                            Observable.fromCallable { true }
                        else -> Observable.error(it)
                    }
                }
            }.subscribeWithErrorHandler(onNext, onComplete, onError, errorHandler)

    protected fun <T> subscribeSingle(single: Single<T>,
                                      onSuccess: (T) -> Unit = {},
                                      onError: (Throwable) -> Unit = {}): Disposable =
            single.retryWhen { errors ->
                errors.flatMap {
                    when (it) {
                        is TokenExpiredException ->
                            onTokenExpiredError().andThen(Flowable.fromCallable { true })
                        is TokenAlreadyRefreshedException ->
                            Flowable.fromCallable { true }
                        else -> Flowable.error(it)
                    }
                }
            }.subscribeWithErrorHandler(onSuccess, onError, errorHandler)

    protected fun <T> subscribeMaybe(maybe: Maybe<T>,
                                     onSuccess: (T) -> Unit = {},
                                     onComplete: () -> Unit,
                                     onError: (Throwable) -> Unit): Disposable =
            maybe.retryWhen { errors ->
                errors.flatMap {
                    when (it) {
                        is TokenExpiredException ->
                            onTokenExpiredError().andThen(Flowable.fromCallable { true })
                        is TokenAlreadyRefreshedException ->
                            Flowable.fromCallable { true }
                        else -> Flowable.error(it)
                    }
                }
            }.subscribeWithErrorHandler(onSuccess, onComplete, onError, errorHandler)

    protected fun subscribeCompletable(completable: Completable,
                                       onComplete: () -> Unit = {},
                                       onError: (Throwable) -> Unit = {}): Disposable =
            completable.retryWhen { errors ->
                errors.flatMap {
                    when (it) {
                        is TokenExpiredException ->
                            onTokenExpiredError().andThen(Flowable.fromCallable { true })
                        is TokenAlreadyRefreshedException ->
                            Flowable.fromCallable { true }
                        else -> Flowable.error(it)
                    }
                }
            }.subscribeWithErrorHandler(onComplete, onError, errorHandler)

    private fun <T> Observable<T>.subscribeWithErrorHandler(onNext: (T) -> Unit = {},
                                                            onComplete: () -> Unit = {},
                                                            onError: (Throwable) -> Unit = {},
                                                            errorHandler: ErrorHandlerView?): Disposable =
            subscribeWith(DefaultObserver(onNext, onComplete, onError, errorHandler))

    private fun <T> Single<T>.subscribeWithErrorHandler(onSuccess: (T) -> Unit = {},
                                                        onError: (Throwable) -> Unit = {},
                                                        errorHandler: ErrorHandlerView?): Disposable =
            subscribeWith(DefaultSingleObserver(onSuccess, onError, errorHandler))

    private fun <T> Maybe<T>.subscribeWithErrorHandler(onSuccess: (T) -> Unit = {},
                                                       onComplete: () -> Unit = {},
                                                       onError: (Throwable) -> Unit = {},
                                                       errorHandler: ErrorHandlerView?): Disposable =
            subscribeWith(DefaultMayeObserver(onSuccess, onComplete, onError, errorHandler))

    private fun Completable.subscribeWithErrorHandler(onComplete: () -> Unit = {},
                                                      onError: (Throwable) -> Unit = {},
                                                      errorHandler: ErrorHandlerView?): Disposable =
            subscribeWith(DefaultCompletableObserver(onComplete, onError, errorHandler))

    abstract fun start(args: Map<String, Any?> = mapOf())

    open fun stop() {}

    private fun onGenericError(error: Int, message: String?) {
        when (error) {
            GenericErrors.ERR_SESSION_EXPIRED -> logout(message)
            GenericErrors.ERR_PERMISSIONS_CHANGED -> refreshTokenAndRestart(message)
        }
    }

    fun refreshToken() = sessionManager.refreshAccessToken()

    private fun onTokenExpiredError() =
            sessionManager.refreshAccessToken()

    private fun refreshTokenAndRestart(message: String? = "") {
        subscribeCompletable(onTokenExpiredError(), onComplete = {
            defaultOnPermissionsChanged(message)
        })
    }

    fun isUserLoggedIn() = sessionManager.isUserLoggedIn()

    fun getLoggedInUser() = sessionManager.getLoggedInUser()

    fun getCurrentInstitute(onSuccess: (InstitutionModel) -> Unit) =
            sessionManager.getCurrentInstitute().subscribeBy(onSuccess = {
                onSuccess(it)
            })

    fun isCurrentInstituteSelected(onSuccess: (Boolean) -> Unit) =
            sessionManager.isCurrentInstituteSelected().subscribeBy {
                onSuccess(it)
            }

    fun setCurrentInstitute(institutionId: String, onSuccess: () -> Unit) =
            sessionManager.setCurrentInstitute(institutionId).subscribeBy(onComplete = {
                onSuccess()
            })

    fun logout(message: String? = "", onLogOutComplete: (String?) -> Unit = defaultOnLogout) {
        sessionManager.logout().subscribe {
            onLogOutComplete(message)
        }
    }

    fun restart() {
        val app = getApplication<Application>()
        app.startActivity(app.intentFor<MainActivity>().clearTask().newTask())
    }
}