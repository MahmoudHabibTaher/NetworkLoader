package com.parent.domain.classes

import com.parent.domain.base.mappers.ModelMapper
import com.parent.entities.ClassModel
import com.parent.entities.ClassView

/**
 * Created by mahmoud on 10/17/17.
 */
class ClassViewModelMapper : ModelMapper<ClassModel, ClassView> {
    override fun mapFrom(from: ClassModel): ClassView =
            ClassView.Builder()
                    .id(from.id)
                    .name(from.name)
                    .avatar(from.avatar)
                    .totalChildren(from.totalChildren)
                    .totalChildrenCheckedIn(from.totalChildrenCheckedIn)
                    .status(from.status)
                    .build()

    override fun mapTo(to: ClassView): ClassModel =
            ClassModel.Builder()
                    .id(to.id)
                    .name(to.name)
                    .avatar(to.avatar)
                    .totalChildren(to.totalChildren)
                    .totalChildrenCheckedIn(to.totalChildrenCheckedIn)
                    .status(to.status)
                    .build()
}