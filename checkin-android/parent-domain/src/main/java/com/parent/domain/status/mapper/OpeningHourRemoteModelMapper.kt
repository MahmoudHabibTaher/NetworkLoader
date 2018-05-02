package com.parent.domain.status.mapper

import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.common.lang.mapRemoteBooleanToString
import com.parent.domain.common.lang.mapRemoteStringToBoolean
import com.parent.entities.OpeningHourDayModel
import com.parent.entities.OpeningHourDayModelRemote

/**
 * Created by mahmoud on 11/28/17.
 */
class OpeningHourRemoteModelMapper : ModelMapper<OpeningHourDayModelRemote,
        OpeningHourDayModel> {
    override fun mapFrom(item: OpeningHourDayModelRemote): OpeningHourDayModel =
            OpeningHourDayModel(item.id ?: "", item.openingHour ?: "", item.closingHour ?: "",
                    item.isWeekend?.mapRemoteStringToBoolean() ?: false, item.dayNumber?.toInt() ?: 0, item.dayName ?: "")

    override fun mapTo(item: OpeningHourDayModel): OpeningHourDayModelRemote =
            OpeningHourDayModelRemote(item.id, item.openingHour, item.closingHour,
                    item.isWeekend.mapRemoteBooleanToString(), item.dayNumber.toString(), item.dayName)
}