package com.parent.domain.realm.entities

import io.realm.RealmObject

/**
 * Created by Raed Ezzat on 15/01/2018.
 */
open class UserStatusItemModelRealm(
        var body: String = "",
        var type: String = "",
        var dateTime: Long = 0,
        var dateTimeText: String = "",
        var child: ChildModelRealm? = ChildModelRealm()
):RealmObject() {}