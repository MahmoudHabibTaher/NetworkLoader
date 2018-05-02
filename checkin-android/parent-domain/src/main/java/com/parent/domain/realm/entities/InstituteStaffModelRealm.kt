package com.parent.domain.realm.entities

import io.realm.RealmObject

/**
 * Created by Raed Ezzat on 18/12/2017.
 */
open class InstituteStaffModelRealm(
        var id: String = "",
        var institutionId: String = "",
        var fullName: String = "",
        var photo: String = "",
        var email: String = ""
) : RealmObject()