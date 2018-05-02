package com.parent.domain.overview

import com.parent.domain.base.mappers.ModelMapper
import com.parent.entities.Status
import com.parent.entities.StatusRemote


class StatusRemoteModelMapper() : ModelMapper<StatusRemote, Status> {

    override fun mapFrom(item: StatusRemote): Status =
            Status.Builder()
                    .name(item.name!!)
                    .total(item.total!!)
                    .type(item.type!!)
                    .build()

    override fun mapTo(item: Status): StatusRemote =
            StatusRemote.Builder()
                    .name(item.name)
                    .total(item.total)
                    .type(item.type)
                    .build()
}