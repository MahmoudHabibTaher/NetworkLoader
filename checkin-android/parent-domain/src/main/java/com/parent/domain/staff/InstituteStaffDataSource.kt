package com.parent.domain.staff

import com.parent.domain.base.BaseDataSource
import com.parent.entities.InstituteStaffModel
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 11/27/17.
 */
interface InstituteStaffDataSource : BaseDataSource {
    fun loadInstituteStaff(institutionId: String): Single<List<InstituteStaffModel>>

    fun saveInstituteStaff(stafflist: List<InstituteStaffModel>): Completable

    fun deleteInstituteStaff(): Completable

}