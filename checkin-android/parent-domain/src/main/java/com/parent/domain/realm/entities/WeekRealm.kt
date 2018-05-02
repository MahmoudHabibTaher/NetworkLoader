package com.parent.domain.realm.entities

import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by mahmoud on 11/25/17.
 */
open class WeekRealm(var number: String = "",
                     var days: RealmList<DayRealm> = RealmList()) : RealmObject()