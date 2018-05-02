package com.parent.domain.realm.entities

import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by raede on 24/11/2017.
 */
open class UserReplyModelRealm(
        var extraChildrenCount: String = "",
        var extraAdultCount: String = "",
        var attendingChildView: RealmList<AttendingChildrenModelRealm> = RealmList()
) : RealmObject() {

}