package com.parent.domain.staff

import com.parent.entities.InstituteStaffModel
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 11/28/17.
 */
class InstituteStaffLocalDataSource(private val staffDao: InstituteStaffDao) : InstituteStaffDataSource {
    override fun loadInstituteStaff(institutionId: String): Single<List<InstituteStaffModel>> =
            staffDao.loadInstituteStaff(institutionId)

    override fun saveInstituteStaff(staff: List<InstituteStaffModel>): Completable =
            staffDao.saveInstituteStaff(staff)

    override fun deleteInstituteStaff(): Completable =
            staffDao.deleteAllInstituteStaff()

    override fun invalidateCache() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}