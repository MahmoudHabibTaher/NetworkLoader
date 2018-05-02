package com.parent.domain.children.pickup

import com.parent.entities.ChildContactModel
import com.parent.entities.ChildPickupModel
import com.parent.entities.ChildContactModelView
import com.parent.entities.ChildPickupModelView
import com.parent.domain.common.lang.mapRemoteBooleanToString
import com.parent.domain.common.lang.mapRemoteStringToBoolean
import com.parent.domain.base.mappers.ModelMapper

/**
 * Created by mahmoud on 10/3/17.
 */
class ChildPickUpModelMapper(var childContactRemoteModelMapper: ModelMapper<ChildContactModel, ChildContactModelView>) : ModelMapper<ChildPickupModel, ChildPickupModelView> {

    override fun mapFrom(item: ChildPickupModel): ChildPickupModelView =
            ChildPickupModelView(item.id, item.childId, item.personId,
                    item.name, item.createdAt, item.updatedAt,
                    item.deletedAt, item.isPerson.mapRemoteStringToBoolean(), item.pickupDateTime,
                    childContactRemoteModelMapper.mapFrom(item.person ?: ChildContactModel()),
                    item.pickupText)

    override fun mapTo(item: ChildPickupModelView): ChildPickupModel =
            ChildPickupModel(item.id, item.childId, item.personId,
                    item.name, item.createdAt, item.updatedAt,
                    item.deletedAt, item.isPerson.mapRemoteBooleanToString(), item.pickupDateTime,
                    childContactRemoteModelMapper.mapTo(item.person), item.pickupText)
}