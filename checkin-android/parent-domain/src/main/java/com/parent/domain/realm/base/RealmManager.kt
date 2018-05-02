package com.parent.domain.realm.base

import android.content.Context
import com.parent.domain.realm.base.IRealmManager.Companion.NAME
import com.parent.domain.realm.base.IRealmManager.Companion.VERSION
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by mahmoud on 9/4/17.
 */
object RealmManager : IRealmManager {
    override fun initRealm(context: Context) {
        Realm.init(context)
        Realm.setDefaultConfiguration(getDefaultConfiguration())
        initRealmBrowser(context)
    }

    override fun getDefaultConfiguration(): RealmConfiguration =
            RealmConfiguration.Builder().apply {
                name(NAME)
                schemaVersion(VERSION)
                migration(Migration())
                modules(UseCasesModule())
            }.build()

    override fun getRealm() = Realm.getDefaultInstance()

    private fun initRealmBrowser(context: Context) {
//        @Suppress("ConstantConditionIf")
//        if (BuildConfig.REALM_BROWSER_ENABLED) {
//            RealmBrowser.Builder(context)
//                    .add(RealmManager.getRealm(), listOf(UserRealm::class.java)).showNotification()
//        }
    }
}