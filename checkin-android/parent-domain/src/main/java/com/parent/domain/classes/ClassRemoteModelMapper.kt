package com.parent.domain.classes

import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.base.mappers.mapFromWith
import com.parent.domain.base.mappers.mapToWith
import com.parent.entities.ClassModel
import com.parent.entities.ClassRemote
import com.parent.entities.ClassSettings
import com.parent.entities.ClassSettingsRemote
import com.parent.entities.Status
import com.parent.entities.StatusRemote

/**
 * Created by mahmoud on 11/7/17.
 */
class ClassRemoteModelMapper(private val classSettingsModelMapper: ModelMapper<ClassSettingsRemote,
        ClassSettings>,private val statusRemoteModelMapper: ModelMapper<StatusRemote, Status>): ModelMapper<ClassRemote, ClassModel>{
    override fun mapFrom(from: ClassRemote): ClassModel =
            ClassModel.Builder()
                    .id(from.id ?: "")
                    .instituteId(from.instituteId ?: "")
                    .name(from.name ?: "")
                    .avatar(from.avatar ?: "")
                    .description(from.description ?: "")
                    .staffRatio(from.staffRatio ?: "")
                    .capacity(from.capacity ?: 0)
                    .phone(from.phone ?: "")
                    .totalChildren(from.totalChildren ?: 0)
                    .totalChildrenCheckedIn(from.totalChildrenCheckedIn ?: 0)
                    .status(from.status?.mapFromWith(statusRemoteModelMapper))
                    .settings(from.settings?.mapFromWith(classSettingsModelMapper)
                            ?: ClassSettings())
                    .build()

    override fun mapTo(to: ClassModel): ClassRemote =
            ClassRemote.Builder()
                    .id(to.id)
                    .name(to.name)
                    .avatar(to.avatar)
                    .totalChildren(to.totalChildren)
                    .totalChildrenCheckedIn(to.totalChildrenCheckedIn)
                    .status(to.status?.mapToWith(statusRemoteModelMapper))
                    .build()
}