package eu.parent.android.app.user.di

import com.github.salomonbrys.kodein.*
import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.common.network.ServiceHelper
import com.parent.domain.permissions.PermissionRemoteModelMapper
import com.parent.domain.roles.RoleRemoteModelMapper
import com.parent.domain.roles.RoleUserRemoteModelMapper
import com.parent.domain.auth.AuthDataSource
import com.parent.domain.user.AuthApi
import com.parent.domain.user.AuthRemoteDataSource
import com.parent.domain.user.LoginDataModelMapper
import com.parent.domain.user.UserRemoteModelMapper
import com.parent.entities.*

/**
 * Created by mahmoud on 9/22/17.
 */
val userRemoteDataModule = Kodein.Module {
    bind<AuthDataSource>("remoteAuthDataSource") with singleton {
        AuthRemoteDataSource(instance(), instance(), instance())
    }

    bind<AuthApi>() with provider {
        ServiceHelper.createService<AuthApi>(instance())
    }

    bind<ModelMapper<LoginData, User>>() with provider { LoginDataModelMapper(instance()) }

    bind<ModelMapper<UserRemote, User>>() with provider {
        UserRemoteModelMapper(RoleRemoteModelMapper(PermissionRemoteModelMapper(), RoleUserRemoteModelMapper(instance())),
                instance(), instance(),instance(),instance(),instance())
    }
}