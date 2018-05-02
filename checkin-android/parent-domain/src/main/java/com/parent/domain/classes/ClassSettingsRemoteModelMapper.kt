package com.parent.domain.classes

import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.base.mappers.mapFromWith
import com.parent.entities.ClassSettings
import com.parent.entities.ClassSettingsRemote
import com.parent.entities.SettingStatus
import com.parent.entities.SettingStatusRemote

class ClassSettingsRemoteModelMapper(private val settingStatusModelMapper: ModelMapper<SettingStatusRemote, SettingStatus>) : ModelMapper<ClassSettingsRemote, ClassSettings> {
    override fun mapFrom(from: ClassSettingsRemote): ClassSettings =
            ClassSettings.Builder().statuses(from.statuses?.mapFromWith(settingStatusModelMapper)
                    ?: listOf()).build()

    override fun mapTo(to: ClassSettings): ClassSettingsRemote {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}