package com.parent.domain.realm.entities

import io.realm.RealmObject

/**
 * Created by Raed Ezzat on 18/12/2017.
 */
open class ChildPickupModelRealm(
        var id: String? = "",
        var childId: String? = "",
        var personId: String? = "",
        var name: String? = "",
        var createdAt: String? = "",
        var updatedAt: String? = "",
        var deletedAt: String? = "",
        var isPerson: String? = "",
        var pickupDateTime: String? = "",
        var person: ChildContactModelRealm? = ChildContactModelRealm(),
        var pickupText: String = ""

) : RealmObject() {

}