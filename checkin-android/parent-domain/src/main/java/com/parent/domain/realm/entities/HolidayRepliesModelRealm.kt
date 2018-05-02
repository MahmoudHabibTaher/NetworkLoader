package com.parent.domain.realm.entities

import io.realm.RealmObject

/**
 * Created by raede on 23/11/2017.
 */

open class HolidayRepliesModelRealm(
        var holidayId: String = "",
        var reply: DayRealm? = DayRealm(),
        var noReply: DayRealm? = DayRealm()
) : RealmObject()

