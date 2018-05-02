package com.parent.domain.classes

import com.parent.entities.ClassModel
import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.realm.entities.ClassRealm

/**
 * Created by mahmoud on 11/13/17.
 */
class ClassRealmModelMapper : ModelMapper<ClassRealm, ClassModel> {
    override fun mapFrom(from: ClassRealm): ClassModel =
            ClassModel.Builder()
                    .id(from.id)
                    .name(from.name)
                    .avatar(from.avatar)
                    .build()

    override fun mapTo(to: ClassModel): ClassRealm =
            ClassRealm.Builder()
                    .id(to.id)
                    .name(to.name)
                    .avatar(to.avatar)
                    .build()
}
