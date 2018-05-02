package com.parent.domain.realm.entities

import io.realm.RealmObject

/**
 * Created by Raed Ezzat on 05/01/2018.
 */
open class ChildContactRelationModelRealm(
        var id:String = "",
        var title:String = ""
):RealmObject()