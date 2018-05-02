package com.parent.domain.classes

import com.parent.entities.ClassModel
import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.base.mappers.mapFromWith
import com.parent.domain.base.mappers.mapToWith
import com.parent.domain.realm.entities.ClassRealm
import com.vicpin.krealmextensions.delete
import com.vicpin.krealmextensions.query
import com.vicpin.krealmextensions.saveAll
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 12/6/17.
 */
class ClassesRealmDao(private val modelMapper: ModelMapper<ClassRealm, ClassModel>) : ClassesDao {
    override fun loadClasses(instituteId: String): Single<List<ClassModel>> =
            Single.fromCallable {
                findClasses(instituteId)
                        .mapFromWith(modelMapper)
            }

    override fun saveClasses(instituteId: String, classes: List<ClassModel>): Completable =
            Completable.fromAction {
                classes.mapToWith(modelMapper).map {
                    it.instituteId = instituteId
                    it
                }.saveAll()
            }

    override fun deleteClasses(instituteId: String): Completable =
            Completable.fromAction {
                ClassRealm().delete { it.equalTo("instituteId", instituteId) }
            }

    private fun findClasses(instituteId: String): List<ClassRealm> =
            ClassRealm().query { it.equalTo("instituteId", instituteId) }
}