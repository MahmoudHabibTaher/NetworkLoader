package com.parent.domain.status

import com.parent.domain.base.mappers.ModelMapper
import com.parent.entities.StatusData
import com.parent.entities.StatusDataRemote

class StatusDataRemoteModelMapper : ModelMapper<StatusDataRemote, StatusData> {
    override fun mapFrom(from: StatusDataRemote): StatusData =
            StatusData.Builder()
                    .name(from.name)
                    .id(from.id)
                    .children(from.children)
                    .staff(from.staff)
                    .avatar(from.avatar)
                    .build()

    override fun mapTo(to: StatusData): StatusDataRemote {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}