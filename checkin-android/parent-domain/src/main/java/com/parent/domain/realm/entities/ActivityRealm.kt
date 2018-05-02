package com.parent.domain.realm.entities

import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by raede on 03/12/2017.
 */
open class ActivityRealm(
        var id: String = "",
        var title: String = "",
        var description: String = "",
        var date: String = "",
        var startTime: String = "",
        var endTime: String = "",
        var photo: String = "",
        var isReminder: Boolean = false,
        var institutionId: String = "",
        var recipients: RealmList<RecipientRealm> = RealmList(),
        var remainingRecipients: String = "",
        var totalInvitedChildren: String = "",
        var totalInvitedAdults: String = "",
        var canDelete: Boolean = false
) : RealmObject() {}