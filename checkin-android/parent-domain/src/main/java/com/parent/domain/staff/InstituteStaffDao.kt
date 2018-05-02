package com.parent.domain.staff

import com.parent.entities.InstituteStaffModel
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 11/28/17.
 */
interface InstituteStaffDao {
    fun loadInstituteStaff(institutionId: String): Single<List<InstituteStaffModel>>


    fun saveInstituteStaff(staff: List<InstituteStaffModel>): Completable


    fun deleteAllInstituteStaff(): Completable
}