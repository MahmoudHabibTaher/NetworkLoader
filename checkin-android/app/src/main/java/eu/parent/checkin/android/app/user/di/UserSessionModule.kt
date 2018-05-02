package eu.parent.android.app.user.di

import com.github.salomonbrys.kodein.*
import com.parent.domain.auth.ISessionManager
import com.parent.domain.auth.ISessionStateManager
import com.parent.domain.auth.SessionManager
import com.parent.domain.auth.SessionStateManager
import com.parent.domain.base.CompletableUseCase
import com.parent.domain.user.session.refresh.RefreshToken

/**
 * Created by mahmoud on 10/27/17.
 */
val userSessionModule = Kodein.Module {
    bind<CompletableUseCase>("refreshTokenUseCase") with provider {
        RefreshToken(instance(), instance(), instance("authDataSource"))
    }

    bind<ISessionManager>() with singleton {
        SessionManager(instance("authDataSource"), instance("refreshTokenUseCase"))
    }

    bind<ISessionStateManager>() with provider {
        SessionStateManager
    }
}