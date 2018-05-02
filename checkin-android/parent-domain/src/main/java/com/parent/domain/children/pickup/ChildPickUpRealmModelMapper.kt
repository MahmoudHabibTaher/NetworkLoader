package com.parent.domain.children.pickup

import com.parent.entities.ChildContactModel
import com.parent.entities.ChildPickupModel
import com.parent.domain.realm.entities.ChildContactModelRealm
import com.parent.domain.realm.entities.ChildPickupModelRealm
import com.parent.domain.base.mappers.ModelMapper

/**
 * Created by mahmoud on 10/3/17.
 */
class ChildPickUpRealmModelMapper(var childContactRemoteModelMapper: ModelMapper<ChildContactModelRealm, ChildContactModel>) : ModelMapper<ChildPickupModelRealm, ChildPickupModel> {

    override fun mapFrom(item: ChildPickupModelRealm): ChildPickupModel =
            ChildPickupModel(item.id ?: "", item.childId ?: "", item.personId
                    ?: "",
                    item.name ?: "", item.createdAt ?: "", item.updatedAt ?: "",
                    item.deletedAt ?: "", item.isPerson ?: "", item.pickupDateTime ?: "",
                    childContactRemoteModelMapper.mapFrom(item.person ?: ChildContactModelRealm()),
                    item.pickupText)

    override fun mapTo(item: ChildPickupModel): ChildPickupModelRealm =
            ChildPickupModelRealm(item.id ?: "", item.childId
                    ?: "", item.personId ?: "",
                    item.name ?: "", item.createdAt ?: "", item.updatedAt ?: "",
                    item.deletedAt ?: "", item.isPerson ?: "", item.pickupDateTime ?: "",
                    childContactRemoteModelMapper.mapTo(item.person),item.pickupText)
}