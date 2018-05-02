package eu.parent.android.app.splash.presentation

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.github.salomonbrys.kodein.Kodein
import com.parent.domain.base.SingleUseCase

/**
 * Created by mahmoud on 9/8/17.
 */
class SplashViewModelFactory(private val application: Application,
                             private val kodein: Kodein,
                             private val isUserLoggedIn: SingleUseCase<Boolean>) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>?): T {
        if (modelClass?.isAssignableFrom(SplashViewModel::class.java) ?: false) {
            return SplashViewModel(application, kodein, isUserLoggedIn) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}