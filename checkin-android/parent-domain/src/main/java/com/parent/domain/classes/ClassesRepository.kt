package com.parent.domain.classes

import com.parent.domain.base.BaseRepository
import com.parent.entities.ClassModel
import com.parent.entities.ClassDetails
import com.parent.entities.Status
import com.parent.entities.exceptions.EmptyListException
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 12/6/17.
 */
class ClassesRepository(private val remoteDataSource: ClassesDataSource,
                        private val localDataSource: ClassesDataSource) : ClassesDataSource, BaseRepository() {

    override fun loadClass(id: String): Single<ClassDetails> =
            remoteDataSource.loadClass(id)

    override fun loadDashboardClasses(instituteId: String): Single<List<ClassModel>> =
            remoteDataSource.loadDashboardClasses(instituteId)

    override fun loadClasses(instituteId: String): Single<List<ClassModel>> =
            when {
                isCacheInvalid() -> loadRemoteClasses(instituteId)
                else -> loadLocalClasses(instituteId)
            }

    override fun loadUserClasses(instituteId: String, userId: String): Single<List<ClassModel>> =
            remoteDataSource.loadUserClasses(instituteId, userId)

    override fun saveClasses(instituteId: String, classes: List<ClassModel>): Completable =
            deleteClasses(instituteId).andThen(localDataSource.saveClasses(instituteId, classes))

    override fun invalidateCache() = setCacheValid(false)

    private fun loadRemoteClasses(instituteId: String) =
            remoteDataSource.loadClasses(instituteId).doOnSuccess {
                saveClasses(instituteId, it).blockingAwait()
            }

    private fun loadLocalClasses(instituteId: String) =
            localDataSource.loadClasses(instituteId).onErrorResumeNext {
                when (it) {
                    is EmptyListException -> loadRemoteClasses(instituteId)
                    else -> Single.error(it)
                }
            }

    override fun deleteClasses(instituteId: String): Completable =
            localDataSource.deleteClasses(instituteId)

    override fun loadClassStatuses(classId: String): Single<List<Status>> =
            remoteDataSource.loadClassStatuses(classId)

    override fun loadClassStatusDetails(classId: String, statusType: String): Single<ClassDetails> =
            remoteDataSource.loadClassStatusDetails(classId, statusType)

    override fun loadClassSettings(classId: String): Single<ClassModel> =
            remoteDataSource.loadClassSettings(classId)
}