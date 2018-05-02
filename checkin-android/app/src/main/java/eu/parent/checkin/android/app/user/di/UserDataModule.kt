package eu.parent.android.app.user.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.parent.domain.auth.AuthDataSource
import com.parent.domain.auth.AuthRepository

/**
 * Created by mahmoud on 9/22/17.
 */
val userDataModule = Kodein.Module {
    import(userLocalDataModule)
    import(userRemoteDataModule)

    bind<AuthDataSource>("authDataSource") with singleton {
        AuthRepository(instance("remoteAuthDataSource"),
                instance("localAuthDataSource"))
    }
}