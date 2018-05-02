package com.parent.domain.children.pickup

import android.util.Log
import com.parent.entities.ChildContactModel
import com.parent.entities.ChildPickupModel
import com.parent.entities.ChildContactModelResponse
import com.parent.entities.ChildPickupModelRemote
import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.datetime.DateFormatter

/**
 * Created by mahmoud on 10/3/17.
 */
class ChildPickUpRemoteModelMapper(var dateFormatter: DateFormatter, var childContactRemoteModelMapper: ModelMapper<ChildContactModelResponse, ChildContactModel>) : ModelMapper<ChildPickupModelRemote, ChildPickupModel> {

    override fun mapFrom(item: ChildPickupModelRemote): ChildPickupModel =
            ChildPickupModel(item.id ?: "", item.childId ?: "", item.userId
                    ?: "",
                    item.name ?: "", item.createdAt ?: "", item.updatedAt ?: "",
                    item.deletedAt ?: "", item.isPerson ?: "", item.pickupDateTime ?: "",
                    childContactRemoteModelMapper.mapFrom(item.person
                            ?: ChildContactModelResponse()))

    override fun mapTo(item: ChildPickupModel): ChildPickupModelRemote =
            ChildPickupModelRemote(null, null
                    ?: "", if (item.personId.equals("")) {
                null
            } else {
                item.personId
            },
                    item.name ?: "", null, null,
                    null, null,
                    dateFormatter.formatServerReciveTimeToGMT(item.pickupDateTime),
                    dateFormatter.formatToServerTimeWithUTCTimeZon(item.pickupDateTime),
                    if (item.person.id.equals("")) {
                        null
                    } else {
                        childContactRemoteModelMapper.mapTo(item.person)
                    })

}