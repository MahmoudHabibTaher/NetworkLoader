package eu.parent.android.app.user.di

import com.github.salomonbrys.kodein.*
import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.realm.base.RealmDBHelper
import com.parent.domain.permissions.PermissionRealmModelMapper
import com.parent.domain.roles.RoleRealmModelMapper
import com.parent.domain.roles.RoleUserRealmModelMapper
import com.parent.domain.auth.AuthDataSource
import com.parent.entities.User
import com.parent.domain.user.AuthLocalDataSource
import com.parent.domain.user.UserDao
import com.parent.domain.user.UserRealmDBHelper
import com.parent.domain.user.UserRealmDao
import com.parent.domain.realm.entities.UserRealm
import com.parent.domain.user.UserRealmModelMapper

/**
 * Created by mahmoud on 9/22/17.
 */
val userLocalDataModule = Kodein.Module {
    bind<AuthDataSource>("localAuthDataSource") with singleton {
        AuthLocalDataSource(instance(), instance())
    }

    bind<UserDao>() with provider {
        UserRealmDao(instance(), instance())
    }

    bind<RealmDBHelper<UserRealm>>() with provider {
        UserRealmDBHelper()
    }

    bind<ModelMapper<UserRealm, User>>() with provider {
        UserRealmModelMapper(RoleRealmModelMapper(RoleUserRealmModelMapper(instance()), PermissionRealmModelMapper()),
                instance(),instance(),instance())
    }
}