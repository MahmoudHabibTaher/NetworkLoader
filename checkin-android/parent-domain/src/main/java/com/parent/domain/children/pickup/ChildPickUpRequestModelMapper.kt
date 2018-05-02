package com.parent.domain.children.pickup

import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.datetime.DateFormatter
import com.parent.entities.*

/**
 * Created by mahmoud on 10/3/17.
 */
class ChildPickUpRequestModelMapper(var dateFormatter: DateFormatter) : ModelMapper<ChildPickupRequest, ChildPickupModel> {
    override fun mapFrom(from: ChildPickupRequest): ChildPickupModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun mapTo(item: ChildPickupModel): ChildPickupRequest =
            ChildPickupRequest(if (item.personId.equals("")) {
                null
            } else {
                item.personId
            }, item.name ?: "",
                    dateFormatter.formatToServerTimeWithUTCTimeZon(item.pickupDateTime))

}