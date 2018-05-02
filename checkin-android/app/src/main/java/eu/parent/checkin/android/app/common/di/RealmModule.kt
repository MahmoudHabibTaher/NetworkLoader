package eu.parent.android.app.common.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.provider
import com.parent.domain.realm.base.RealmManager
import io.realm.Realm

/**
 * Created by mahmoud on 11/27/17.
 */
val realmModule = Kodein.Module {
    bind<Realm>() with provider {
        RealmManager.getRealm()
    }
}