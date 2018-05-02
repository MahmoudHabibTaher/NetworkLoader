package com.parent.domain.classes

import com.parent.entities.ClassModel
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 12/6/17.
 */
interface ClassesDao {
    fun loadClasses(instituteId: String): Single<List<ClassModel>>

    fun saveClasses(instituteId: String, classes: List<ClassModel>): Completable

    fun deleteClasses(instituteId: String): Completable
}