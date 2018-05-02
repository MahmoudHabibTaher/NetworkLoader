package com.parent.domain.status.mapper

import com.parent.domain.base.mappers.ModelMapper
import com.parent.entities.*

/**
 * Created by ahmedmahmoud on 11/28/17.
 */
class InstituteMealRemoteModelMapper : ModelMapper<InstituteMealRemote,
        InstituteMeal> {
    override fun mapFrom(item: InstituteMealRemote): InstituteMeal =
            InstituteMeal(item.id ?: 0, item.name ?: "")

    override fun mapTo(item: InstituteMeal): InstituteMealRemote =
            InstituteMealRemote(item.id, item.name)

}