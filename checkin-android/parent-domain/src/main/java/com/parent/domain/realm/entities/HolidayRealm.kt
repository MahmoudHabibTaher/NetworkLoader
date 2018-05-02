package com.parent.domain.realm.entities

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by raede on 23/11/2017.
 */

open class HolidayRealm(
        @PrimaryKey
        var id: String = "",
        var instituteId: String = "",
        var title: String = "",
        var description: String = "",
        var startDate: Long = -1,
        var endDate: Long = -1,
        var deadline: Long = -1,
        var remainingDays: String = "",
        var recipients: RealmList<RecipientRealm> = RealmList(),
        var remainingRecipients: String = "",
        var days: RealmList<DayRealm> = RealmList(),
        var weeks: RealmList<WeekRealm> = RealmList(),
        var recipientsCanReply: RealmList<RecipientRealm> = RealmList(),
        var canReply: String = "",
        var totalReplied: String = "",
        var totalNoReplied: String = "",
        var page: Int = 1,
        var dateTimeText: String = "") : RealmObject() {
}

