package com.parent.domain.classes

import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.base.mappers.mapFromWith
import com.parent.entities.*

class ClassDetailsRemoteModelMapper(private val classModelMapper: ModelMapper<ClassRemote, ClassModel>,
                                    private val classChildModelMapper: ModelMapper<ClassChildRemote, ClassChild>,
                                    private val classStaffModelMapper: ModelMapper<ClassStaffRemote, ClassStaff>)
    : ModelMapper<ClassDetailsRemote, ClassDetails> {

    override fun mapFrom(from: ClassDetailsRemote): ClassDetails =
            ClassDetails.Builder()
                    .clazz(from.clazz?.mapFromWith(classModelMapper) ?: ClassModel())
                    .currentChildren(from.children?.current?.children?.mapFromWith(classChildModelMapper)
                            ?: listOf())
                    .totalCurrentChildren(from.children?.current?.total ?: 0)
                    .staff(from.staff?.staff?.mapFromWith(classStaffModelMapper) ?: listOf())
                    .totalStaff(from.staff?.total ?: 0)
                    .futureChildren(from.children?.future?.children?.mapFromWith(classChildModelMapper)
                            ?: listOf())
                    .totalFutureChildren(from.children?.future?.total ?: 0)
                    .build()

    override fun mapTo(to: ClassDetails): ClassDetailsRemote {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}