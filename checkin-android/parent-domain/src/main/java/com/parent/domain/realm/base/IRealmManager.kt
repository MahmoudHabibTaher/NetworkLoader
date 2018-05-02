package com.parent.domain.realm.base

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by mahmoud on 12/8/17.
 */
interface IRealmManager {

    companion object {
        const val NAME = "library.realm"
        const val VERSION = 1L
    }


    fun initRealm(context: Context)

    fun getDefaultConfiguration(): RealmConfiguration

    fun getRealm(): Realm
}