package com.parent.domain.status.mapper

import com.parent.domain.base.mappers.ModelMapper
import com.parent.entities.Status
import com.parent.entities.StatusRemote

class StatusRemoteModelMapper : ModelMapper<StatusRemote, Status> {
    override fun mapFrom(from: StatusRemote): Status =
            Status.Builder().name(from.name ?: "")
                    .type(from.type ?: "").total(from.total?:0)
                    .build()

    override fun mapTo(to: Status): StatusRemote {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}