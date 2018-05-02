package com.parent.domain.children.data

import com.parent.entities.*
import com.parent.entities.exceptions.EmptyListException
import com.parent.entities.exceptions.ItemNotFoundException
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 12/8/17.
 */
class ChildrenLocalDataSource(private val childrenDao: ChildrenDao) : ChildrenDataSource {
    override fun loadChildGallery(childId: String, type: String,pagenumber:Int): Single<ChildGalleryModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun reportChildSick(childLeaveRequestModel: ChildLeaveRequestModel): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun reportChildVacation(childLeaveRequestModel: ChildLeaveRequestModel): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadChildPickUp(childId: String): Single<ChildPickupModel> =
            childrenDao.loadChildPickUp(childId).flatMap {
                if (it.pickupDateTime.isEmpty()) Single.error(ItemNotFoundException(""))
                else Single.just(it)
            }

    override fun addChildPickUpInfo(childId: String, childPickUpModel: ChildPickupModel): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun getChildHealthDetails(childId: String, institutionId: String): Single<ChildHealthModel> =
            childrenDao.loadChildHealthDetails(childId, institutionId).flatMap {
                if (it.doctorFullName.isEmpty()) Single.error(EmptyListException(""))
                else Single.just(it)
            }

    override fun editChildHealthDetails(childId: String, institutionId: String, childHealth: ChildHealthModel): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun editChildDetails(childId: String, child: ChildModel): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadChildDetails(childId: String, institutionId: String): Single<ChildModel> =
            childrenDao.loadChildDetails(childId).flatMap {
                if (it.fullName.isEmpty()) Single.error(EmptyListException(""))
                else Single.just(it)
            }

    override fun loadClassChildren(classId: String): Single<List<ClassChild>> =
            childrenDao.loadClassChildren(classId).flatMap {
                if (it.isEmpty()) Single.error(EmptyListException(""))
                else Single.just(it)
            }

    override fun saveClassChildren(classId: String, children: List<ClassChild>): Completable =
            childrenDao.saveClassChildren(classId, children)

    override fun deleteClassChildren(classId: String): Completable =
            childrenDao.deleteClassChildren(classId)

    override fun loadChildLeaves(childId: String): Single<ChildLeaves> =
            childrenDao.loadChildLeaves(childId)

    override fun saveChildLeaves(childId: String, childLeaves: ChildLeaves): Completable =
            childrenDao.saveChildLeaves(childId, childLeaves)

    override fun deleteChildLeaves(childId: String): Completable =
            childrenDao.deleteChildLeaves(childId)

    override fun loadChildDailyReport(childId: String, date: Long): Single<DailyReport> =
            childrenDao.loadChildDailyReport(childId, date)

    override fun saveChildDailyReport(dailyReport: DailyReport): Completable =
            childrenDao.saveChildDailyReport(dailyReport)

    override fun deleteChildDailyReport(childId: String, date: Long): Completable =
            childrenDao.deleteChildDailyReport(childId, date)

    override fun loadChildPermissions(childId: String, institutionId: String): Single<List<ChildPermission>> =
            childrenDao.loadChildPermissions(childId)

    override fun addChildPermission(childId: String, institueId: String, permissionName: String, permissionDescription: String): Single<AddChildPermissionResult> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveChildPermissions(childId: String, permissions: List<ChildPermission>): Completable =
            childrenDao.saveChildPermissions(childId, permissions)

    override fun deleteChildPermissions(childId: String): Completable =
            childrenDao.deleteChildPermissions(childId)

    override fun saveChildPermissionReply(childId: String, contactId: String, permissionId: String,
                                          reply: PermissionReply.Status): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun checkInChild(childId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun checkOutChild(childId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun invalidateCache() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}