package com.parent.domain.children.data

import com.parent.entities.ChildHealthModel
import com.parent.entities.ChildModel
import com.parent.entities.ChildPickupModel
import com.parent.entities.ClassChild
import com.parent.entities.ChildLeaves
import com.parent.entities.ChildPermission
import com.parent.entities.DailyReport
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 12/8/17.
 */
interface ChildrenDao {
    fun loadClassChildren(classId: String): Single<List<ClassChild>>

    fun loadChildDetails(childId: String): Single<ChildModel>

    fun loadChildHealthDetails(childId: String, institutionId: String): Single<ChildHealthModel>

    fun loadChildPickUp(childId: String): Single<ChildPickupModel>

    fun saveClassChildren(classId: String, children: List<ClassChild>): Completable

    fun deleteClassChildren(classId: String): Completable

    fun loadChildLeaves(childId: String): Single<ChildLeaves>

    fun saveChildLeaves(childId: String, childLeaves: ChildLeaves): Completable

    fun deleteChildLeaves(childId: String): Completable

    fun loadChildDailyReport(childId: String, date: Long): Single<DailyReport>

    fun saveChildDailyReport(dailyReport: DailyReport): Completable

    fun deleteChildDailyReport(childId: String, date: Long): Completable

    fun loadChildPermissions(childId: String): Single<List<ChildPermission>>

    fun saveChildPermissions(childId: String, permissions: List<ChildPermission>): Completable

    fun deleteChildPermissions(childId: String): Completable
}