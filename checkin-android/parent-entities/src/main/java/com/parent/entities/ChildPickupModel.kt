package com.parent.entities

import com.parent.entities.ChildContactModel

/**
 * Created by Raed Ezzat on 18/12/2017.
 */
class ChildPickupModel(
        var id: String = "",
        var childId: String = "",
        var personId: String = "",
        var name: String = "",
        var createdAt: String = "",
        var updatedAt: String = "",
        var deletedAt: String = "",
        var isPerson: String = "",
        var pickupDateTime: String = "",
        var person: ChildContactModel = ChildContactModel(),
        var pickupText: String = ""
) {

}