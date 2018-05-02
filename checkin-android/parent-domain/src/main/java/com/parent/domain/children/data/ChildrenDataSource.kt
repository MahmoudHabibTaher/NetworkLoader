package com.parent.domain.children.data

import com.parent.domain.base.BaseDataSource
import com.parent.entities.*
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 12/6/17.
 */
interface ChildrenDataSource : BaseDataSource {
    fun loadClassChildren(classId: String): Single<List<ClassChild>>

    fun loadChildPickUp(childId: String): Single<ChildPickupModel>

    fun addChildPickUpInfo(childId: String, childPickUpModel: ChildPickupModel): Completable

    fun reportChildVacation(childLeaveRequestModel: ChildLeaveRequestModel): Completable

    fun reportChildSick(childLeaveRequestModel: ChildLeaveRequestModel): Completable

    fun saveClassChildren(classId: String, children: List<ClassChild>): Completable

    fun deleteClassChildren(classId: String): Completable

    fun loadChildDetails(childId: String, institutionId: String): Single<ChildModel>

    fun loadChildGallery(childId: String, type: String , pageNumber: Int ): Single<ChildGalleryModel>

    fun editChildDetails(childId: String, child: ChildModel): Completable

    fun loadChildLeaves(childId: String): Single<ChildLeaves>

    fun saveChildLeaves(childId: String, childLeaves: ChildLeaves): Completable

    fun deleteChildLeaves(childId: String): Completable

    fun getChildHealthDetails(childId: String, institutionId: String): Single<ChildHealthModel>

    fun editChildHealthDetails(childId: String, institutionId: String, childHealth: ChildHealthModel): Completable

    fun loadChildDailyReport(childId: String, date: Long): Single<DailyReport>

    fun saveChildDailyReport(dailyReport: DailyReport): Completable

    fun deleteChildDailyReport(childId: String, date: Long): Completable

    fun loadChildPermissions(childId: String, institutionId: String): Single<List<ChildPermission>>

    fun addChildPermission(childId: String, instituteId: String, permissionName: String, permissionDescription: String):
            Single<AddChildPermissionResult>

    fun saveChildPermissions(childId: String, permissions: List<ChildPermission>): Completable

    fun deleteChildPermissions(childId: String): Completable

    fun saveChildPermissionReply(childId: String, contactId: String, permissionId: String, reply: PermissionReply.Status): Completable

    fun checkInChild(childId: String): Completable

    fun checkOutChild(childId: String): Completable
}