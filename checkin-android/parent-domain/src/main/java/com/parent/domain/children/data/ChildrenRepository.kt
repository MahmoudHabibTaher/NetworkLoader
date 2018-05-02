package com.parent.domain.children.data

import com.parent.domain.base.BaseRepository
import com.parent.entities.*
import com.parent.entities.exceptions.EmptyListException
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 12/6/17.
 */
class ChildrenRepository(private val remoteDataSource: ChildrenDataSource,
                         private val localDataSource: ChildrenDataSource) : BaseRepository(), ChildrenDataSource {
    override fun loadClassChildren(classId: String): Single<List<ClassChild>> =
            when {
                isCacheInvalid() -> loadRemoteClassChildren(classId)
                else -> loadLocalClassChildren(classId)
            }

    override fun loadChildPickUp(childId: String): Single<ChildPickupModel> =
            remoteDataSource.loadChildPickUp(childId)

    override fun addChildPickUpInfo(childId: String, childPickUpModel: ChildPickupModel): Completable =
            remoteDataSource.addChildPickUpInfo(childId, childPickUpModel)

    override fun reportChildVacation(childLeaveRequestModel: ChildLeaveRequestModel): Completable =
            remoteDataSource.reportChildVacation(childLeaveRequestModel)

    override fun reportChildSick(childLeaveRequestModel: ChildLeaveRequestModel): Completable =
            remoteDataSource.reportChildSick(childLeaveRequestModel)

    override fun editChildDetails(childId: String, child: ChildModel): Completable =
            remoteDataSource.editChildDetails(childId, child)

    override fun saveClassChildren(classId: String, children: List<ClassChild>): Completable =
            deleteClassChildren(classId)
                    .andThen(localDataSource.saveClassChildren(classId, children))

    override fun deleteClassChildren(classId: String): Completable =
            localDataSource.deleteClassChildren(classId)

    override fun invalidateCache() =
            setCacheValid(false)

    override fun loadChildDetails(childId: String, institutionId: String): Single<ChildModel> =
            remoteDataSource.loadChildDetails(childId, institutionId)

    override fun loadChildLeaves(childId: String): Single<ChildLeaves> =
            remoteDataSource.loadChildLeaves(childId)

    override fun saveChildLeaves(childId: String, childLeaves: ChildLeaves): Completable =
            deleteChildLeaves(childId).andThen(localDataSource.saveChildLeaves(childId, childLeaves))

    override fun deleteChildLeaves(childId: String): Completable =
            localDataSource.deleteChildLeaves(childId)

    private fun loadRemoteClassChildren(classId: String): Single<List<ClassChild>> =
            remoteDataSource.loadClassChildren(classId).doOnSuccess {
                saveClassChildren(classId, it).doOnComplete {
                    setCacheValid(true)
                }.subscribe()
            }

    private fun loadLocalClassChildren(classId: String): Single<List<ClassChild>> =
            localDataSource.loadClassChildren(classId).onErrorResumeNext {
                when (it) {
                    is EmptyListException -> loadRemoteClassChildren(classId)
                    else -> Single.error(it)
                }
            }

    override fun getChildHealthDetails(childId: String, institutionId: String): Single<ChildHealthModel> =
            remoteDataSource.getChildHealthDetails(childId, institutionId)

    override fun editChildHealthDetails(childId: String, institutionId: String, childHealth: ChildHealthModel): Completable =
            remoteDataSource.editChildHealthDetails(childId, institutionId, childHealth)

    override fun loadChildDailyReport(childId: String, date: Long): Single<DailyReport> =
            remoteDataSource.loadChildDailyReport(childId, date)

    override fun saveChildDailyReport(dailyReport: DailyReport): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteChildDailyReport(childId: String, date: Long): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadChildPermissions(childId: String, institutionId: String): Single<List<ChildPermission>> =
            remoteDataSource.loadChildPermissions(childId, institutionId)

    override fun addChildPermission(childId: String, instituteId: String, permissionName: String,
                                    permissionDescription: String): Single<AddChildPermissionResult> =
            remoteDataSource.addChildPermission(childId, instituteId, permissionName, permissionDescription)

    override fun saveChildPermissions(childId: String, permissions: List<ChildPermission>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteChildPermissions(childId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun checkInChild(childId: String): Completable =
            remoteDataSource.checkInChild(childId)

    override fun checkOutChild(childId: String): Completable =
            remoteDataSource.checkOutChild(childId)

    override fun saveChildPermissionReply(childId: String, contactId: String, permissionId: String,
                                          reply: PermissionReply.Status): Completable =
            remoteDataSource.saveChildPermissionReply(childId, contactId, permissionId, reply)


    override fun loadChildGallery(childId: String, type: String ,pageNumber: Int): Single<ChildGalleryModel> =
            remoteDataSource.loadChildGallery(childId,type,pageNumber)

}
