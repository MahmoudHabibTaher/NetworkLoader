package eu.parent.android.app.user.di

import android.arch.lifecycle.ViewModelProviders
import com.github.salomonbrys.kodein.*
import com.parent.domain.base.BaseCompletableUseCase
import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.CompletableUseCase
import com.parent.domain.user.CallRefreshUser
import eu.parent.android.app.common.presentation.actvities.BaseActivity
import eu.parent.android.app.domain.user.GetPushNotificationToken
import com.parent.domain.user.SavePushNotificationToken
import com.parent.domain.user.UserLogin
import eu.parent.android.app.user.auth.login.presentation.LoginViewModel
import eu.parent.android.app.user.auth.login.presentation.LoginViewModelFactory

/**
 * Created by mahmoud on 9/22/17.
 */
val loginModule = Kodein.Module {
    bind<LoginViewModel>() with factory { activity: BaseActivity ->
        ViewModelProviders.of(activity, LoginViewModelFactory(instance(), kodein,
                instance("userLoginUseCase"), instance("getPushNotificationToken"), instance("savePushNotificationToken"), instance("getUserLastEmail"))).get(LoginViewModel::class.java)
    }

    bind<CompletableUseCase>() with provider {
        UserLogin(instance(), instance(), instance("authDataSource"))
    }


    bind<BaseSingleUseCase<String>>("getPushNotificationToken") with provider {
        GetPushNotificationToken(instance(), instance(), instance())
    }

    bind<BaseCompletableUseCase>("savePushNotificationToken") with provider {
        SavePushNotificationToken(instance(), instance(), instance("authDataSource"))
    }

    bind<BaseCompletableUseCase>("callRefreshUserUseCase") with provider {
        CallRefreshUser(instance(), instance(), instance("authDataSource"))
    }

}