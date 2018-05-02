package com.parent.domain.realm.entities

import io.realm.RealmObject

/**
 * Created by Raed Ezzat on 15/01/2018.
 */
open class AuthorModelRealm(
        var id: String = "",
        var name: String = "",
        var photo: String = "",
        var subjectId: String = "",
        var type: String = "") : RealmObject()