package com.parent.domain.realm.entities

import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by Raed Ezzat on 15/01/2018.
 */
open class UserModelRealm(
        var id: String = "",
        var fullName: String = "",
        var textDisplay: String = "",
        var avatar: String = "",
        var isContact: Boolean = false,
        var type: Int = 0,
        var role: RoleRealm? = RoleRealm(),
        var classesList: RealmList<ClassRealm>? = RealmList(),
        var children: RealmList<ChildModelRealm>? = RealmList()
) : RealmObject() {
}