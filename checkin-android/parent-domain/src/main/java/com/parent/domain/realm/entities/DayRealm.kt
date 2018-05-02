package com.parent.domain.realm.entities

import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by mahmoud on 11/25/17.
 */
open class DayRealm(var id: String = "",
                    var dayDate: Long = -1,
                    var comment: String = "",
                    var coming: String = "",
                    var classes: RealmList<HolidayComingGroupModelRealm> = RealmList(),
                    var institutions: RealmList<HolidayComingGroupModelRealm> = RealmList(),
                    var groups: RealmList<HolidayComingGroupModelRealm> = RealmList(),
                    var individuals: RealmList<HolidayComingChildModelRealm> = RealmList(),
                    var attending: RealmList<AttendingRealm> = RealmList()) : RealmObject()