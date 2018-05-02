package com.parent.domain.classes

import com.parent.domain.base.mappers.ModelMapper
import com.parent.entities.SettingStatus
import com.parent.entities.SettingStatusRemote

class SettingStatusRemoteModelMapper : ModelMapper<SettingStatusRemote, SettingStatus> {
    override fun mapFrom(from: SettingStatusRemote): SettingStatus =
            SettingStatus.Builder().name(from.name ?: "")
                    .key(from.key ?: "")
                    .relatedKey(from.relatedKey ?: "")
                    .value(from.value ?: false)
                    .hint(from.hint ?: "")
                    .build()

    override fun mapTo(to: SettingStatus): SettingStatusRemote {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}