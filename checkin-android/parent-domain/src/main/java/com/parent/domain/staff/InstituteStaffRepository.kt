package com.parent.domain.staff

import com.parent.domain.base.BaseRepository
import com.parent.entities.exceptions.EmptyListException
import com.parent.entities.InstituteStaffModel
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 11/27/17.
 */
class InstituteStaffRepository(private val remoteDataSource: InstituteStaffDataSource,
                               private val localDataSource: InstituteStaffDataSource) : BaseRepository(), InstituteStaffDataSource {
    override fun loadInstituteStaff(institutionId: String): Single<List<InstituteStaffModel>> =
            when {
                isCacheInvalid() -> loadRemoteInstituteStaff(institutionId)
                else -> {
                    loadLocalInstituteStaff(institutionId).onErrorResumeNext {
                        if (it is EmptyListException) {
                            loadRemoteInstituteStaff(institutionId)
                        } else {
                            Single.error(it)
                        }
                    }
                }
            }

    override fun saveInstituteStaff(staffList: List<InstituteStaffModel>): Completable =
            deleteInstituteStaff().andThen(localDataSource.saveInstituteStaff(staffList))

    override fun deleteInstituteStaff(): Completable =
            localDataSource.deleteInstituteStaff()

    private fun loadRemoteInstituteStaff(institutionId: String) =
            remoteDataSource.loadInstituteStaff(institutionId).doOnSuccess {
                saveInstituteStaff(it).doOnComplete { setCacheValid(true) }.blockingAwait()
            }

    private fun loadLocalInstituteStaff(institutionId: String) =
            localDataSource.loadInstituteStaff(institutionId)

    override fun invalidateCache() =
            setCacheValid(false)
}