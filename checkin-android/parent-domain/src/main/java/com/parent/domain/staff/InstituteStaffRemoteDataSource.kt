package com.parent.domain.staff

import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.base.mappers.mapFromWith
import com.parent.domain.common.data.source.remote.BaseRemoteDataSource
import com.parent.entities.InstituteStaffModel
import com.parent.entities.InstituteStaffModelRemote
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 11/28/17.
 */
class InstituteStaffRemoteDataSource(private val staffApi: InstituteStaffApi,
                                     private val staffRemoteModelMapper: ModelMapper<InstituteStaffModelRemote, InstituteStaffModel>
) : InstituteStaffDataSource, BaseRemoteDataSource() {
    override fun loadInstituteStaff(institutionId: String): Single<List<InstituteStaffModel>> =
            callSingle(staffApi.getInstituteStaff(institutionId).flatMap {
                Single.fromCallable {
                    it.data?.mapFromWith(staffRemoteModelMapper) ?: emptyList()
                }
            })

    override fun saveInstituteStaff(staff: List<InstituteStaffModel>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun deleteInstituteStaff(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun invalidateCache() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}