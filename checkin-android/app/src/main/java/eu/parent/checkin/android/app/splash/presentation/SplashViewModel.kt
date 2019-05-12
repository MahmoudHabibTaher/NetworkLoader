package eu.parent.checkin.android.app.splash.presentation

import android.app.Application
import com.github.salomonbrys.kodein.Kodein
import com.parent.domain.base.SingleUseCase
import eu.parent.android.app.common.presentation.viewmodels.KodeinBaseViewModel
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error

/**
 * Created by mahmoud on 9/8/17.
 */
class SplashViewModel(application: Application,
                      kodein: Kodein,
                      private val isUserLoggedIn: SingleUseCase<Boolean>) : KodeinBaseViewModel(application, kodein), AnkoLogger {

    val showLoginObservable = PublishSubject.create<Boolean>()
    val showHomeObservable = PublishSubject.create<Boolean>()

    override fun start(args: Map<String, Any?>) {
        checkUserLoggedIn()
    }

    fun checkUserLoggedIn() {
        isLoadingVisible = true
        isUserLoggedIn.getSingle().subscribeBy(onSuccess = {
            if (it) {
                isLoadingVisible = false
                showHomeObservable.onNext(true)
            } else {
                showLoginObservable.onNext(true)
            }
        }, onError = {
            error("Error loading is user logged in", it)
        })
    }
}