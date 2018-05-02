package com.parent.domain.classes

import com.parent.domain.base.BaseDataSource
import com.parent.entities.ClassModel
import com.parent.entities.ClassDetails
import com.parent.entities.Status
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 12/6/17.
 */
interface ClassesDataSource : BaseDataSource {
    fun loadClass(id: String): Single<ClassDetails>

    fun loadClasses(instituteId: String): Single<List<ClassModel>>

    fun loadDashboardClasses(instituteId: String): Single<List<ClassModel>>

    fun loadUserClasses(instituteId: String, userId: String): Single<List<ClassModel>>

    fun saveClasses(instituteId: String, classes: List<ClassModel>): Completable

    fun deleteClasses(instituteId: String): Completable

    fun loadClassStatuses(classId: String): Single<List<Status>>

    fun loadClassStatusDetails(classId: String, statusType: String): Single<ClassDetails>

    fun loadClassSettings(classId: String): Single<ClassModel>
}