package com.parent.domain.staff

import com.vicpin.krealmextensions.*
import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.base.mappers.mapFromWith
import com.parent.domain.base.mappers.mapToWith
import com.parent.domain.realm.entities.InstituteStaffModelRealm
import com.parent.entities.InstituteStaffModel
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 11/28/17.
 */
class InstituteStaffRealmDao(private val InstituteStaffRealmModelMapper: ModelMapper<InstituteStaffModelRealm, InstituteStaffModel>
) : InstituteStaffDao {
    override fun loadInstituteStaff(institutionId: String): Single<List<InstituteStaffModel>> =
            Single.fromCallable {
                InstituteStaffModelRealm().query { it.equalTo("institutionId", institutionId) }.map {
                    it mapFromWith InstituteStaffRealmModelMapper
                }
            }

    override fun saveInstituteStaff(staff: List<InstituteStaffModel>): Completable =
            Completable.fromAction {
                (staff mapToWith InstituteStaffRealmModelMapper).saveAll()
            }

    override fun deleteAllInstituteStaff(): Completable =
            Completable.fromAction {
                InstituteStaffModelRealm().deleteAll()
            }

    private fun findInstituteStaff(id: String): InstituteStaffModelRealm? =
            InstituteStaffModelRealm().queryFirst { it.equalTo("id", id) }
}