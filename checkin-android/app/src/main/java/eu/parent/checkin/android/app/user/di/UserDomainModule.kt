package eu.parent.android.app.user.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.provider
import com.parent.domain.auth.GetLastUserEmail
import com.parent.domain.base.BaseCompletableUseCase
import com.parent.domain.base.CompletableUseCase
import com.parent.domain.base.SingleUseCase
import com.parent.domain.auth.IsUserLoggedIn
import com.parent.domain.auth.LogUserOut
import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.user.UserLogin
import com.parent.entities.User
import eu.parent.android.app.domain.user.UserViewModelMapper
import eu.parent.android.app.entities.UserViewModel

/**
 * Created by mahmoud on 9/25/17.
 */
val userDomainModule = Kodein.Module {
    bind<CompletableUseCase>("userLoginUseCase") with provider {
        UserLogin(instance(), instance(), instance("authDataSource"))
    }

    bind<SingleUseCase<Boolean>>("isUserLoggedInUseCase") with provider {
        IsUserLoggedIn(instance(), instance(), instance("authDataSource"))
    }
    bind<SingleUseCase<String>>("getUserLastEmail") with provider {
        GetLastUserEmail(instance(), instance(), instance("authDataSource"))
    }
    bind<BaseCompletableUseCase>("logUserOutUseCase") with provider {
        LogUserOut(instance(), instance(), instance("authDataSource"))
    }

    bind<ModelMapper<User, UserViewModel>>() with provider {
        UserViewModelMapper(instance(), instance(), instance(), instance(),instance())
    }

}