package com.parent.domain.realm.base

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by mahmoud on 12/8/17.
 */
class RealmTestManager : IRealmManager {
    override fun initRealm(context: Context) {

    }

    override fun getDefaultConfiguration(): RealmConfiguration =
            RealmConfiguration.Builder()
                    .inMemory()
                    .name("test-realm")
                    .build()

    override fun getRealm(): Realm =
            Realm.getInstance(getDefaultConfiguration())
}