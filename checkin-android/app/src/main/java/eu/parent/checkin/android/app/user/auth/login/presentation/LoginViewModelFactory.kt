package eu.parent.android.app.user.auth.login.presentation

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.github.salomonbrys.kodein.Kodein
import com.parent.domain.base.BaseCompletableUseCase
import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.CompletableUseCase
import com.parent.domain.base.SingleUseCase

/**
 * Created by mahmoud on 9/8/17.
 */
class LoginViewModelFactory(private val application: Application,
                            private val kodein: Kodein,
                            private val userLogin: CompletableUseCase,
                            private val getOneSignalToken: BaseSingleUseCase<String>,
                            private val saveOneSignalToken: BaseCompletableUseCase,private val getUserLastEmail: SingleUseCase<String>) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>?): T {
        if (modelClass?.isAssignableFrom(LoginViewModel::class.java) ?: false) {
            return LoginViewModel(application, kodein, userLogin, getOneSignalToken, saveOneSignalToken,getUserLastEmail) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}