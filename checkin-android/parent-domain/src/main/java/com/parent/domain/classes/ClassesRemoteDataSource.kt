package com.parent.domain.classes

import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.base.mappers.mapFromWith
import com.parent.domain.common.data.source.remote.BaseRemoteDataSource
import com.parent.entities.*
import com.parent.entities.exceptions.ParseResponseException
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 12/6/17.
 */
class ClassesRemoteDataSource(private val api: ClassesApi,
                              private val classDetailsModelMapper: ModelMapper<ClassDetailsRemote, ClassDetails>,
                              private val classModelMapper: ModelMapper<ClassRemote, ClassModel>,
                              private val statusRemoteModelMapper: ModelMapper<StatusRemote, Status>) : ClassesDataSource, BaseRemoteDataSource() {

    override fun loadClass(id: String): Single<ClassDetails> =
            callSingle(api.getClassDetails(id).flatMap {
                it.data?.mapFromWith(classDetailsModelMapper)?.let {
                    Single.just(it)
                } ?: Single.error(ParseResponseException())
            })

    override fun loadDashboardClasses(instituteId: String): Single<List<ClassModel>> =
            callSingle(api.getDashboardClasses(instituteId).flatMap {
                mapClasses(it.data?.classes)
            })

    override fun loadClasses(instituteId: String): Single<List<ClassModel>> =
            callSingle(api.getClasses(instituteId).flatMap {
                mapClasses(it.data)
            })

    override fun loadUserClasses(instituteId: String, userId: String): Single<List<ClassModel>> =
            callSingle(api.getUserClasses(instituteId, userId).flatMap {
                mapClasses(it.data)
            })

    override fun saveClasses(instituteId: String, classes: List<ClassModel>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteClasses(instituteId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadClassStatuses(classId: String): Single<List<Status>> =
            callSingle(api.getClassStatuses(classId).flatMap {
                Single.just(it.data?.statuses?.mapFromWith(statusRemoteModelMapper) ?: listOf())
            })

    override fun loadClassStatusDetails(classId: String, statusType: String): Single<ClassDetails> =
            callSingle(api.getClassStatusDetails(classId, statusType).flatMap {
                it.data?.mapFromWith(classDetailsModelMapper)?.let {
                    Single.just(it)
                } ?: Single.error(ParseResponseException())
            })

    override fun loadClassSettings(classId: String): Single<ClassModel> =
            callSingle(api.getClassSettings(classId).flatMap {
                it.data?.mapFromWith(classModelMapper)?.let {
                    Single.just(it)
                } ?: Single.error(ParseResponseException())
            })

    override fun invalidateCache() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun mapClasses(data: List<ClassRemote>?) =
            Single.just(data?.mapFromWith(classModelMapper) ?: emptyList())
}