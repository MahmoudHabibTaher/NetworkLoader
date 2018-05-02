package com.parent.domain.realm.base

import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by mahmoud on 10/30/17.
 */
fun <T : RealmObject> List<T>.toRealmList(): RealmList<T> =
        RealmList<T>().let {
            it.addAll(this)
            return@let it
        }