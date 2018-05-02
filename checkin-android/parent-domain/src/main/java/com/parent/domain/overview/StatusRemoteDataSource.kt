package com.parent.domain.overview

import android.util.Log
import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.base.mappers.mapFromWith
import com.parent.domain.common.data.source.remote.BaseRemoteDataSource
import com.parent.entities.Status
import com.parent.entities.StatusRemote
import io.reactivex.Single

/**
 * Created by ahmedmahmoud on 2/17/18.
 */
class StatusRemoteDataSource(private val api: StatusesApi,
                             private val modelMapper: ModelMapper<StatusRemote, Status>) : StatusDataSource, BaseRemoteDataSource() {
    override fun invalidateCache() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadDashboadStatus(instituteId: String): Single<List<Status>> =
            callSingle(api.getInstituteStatus(instituteId).flatMap {
                mapStatuses(it.data!!.status)
            })

    private fun mapStatuses(data: List<StatusRemote>?) =
            Single.just(data?.mapFromWith(modelMapper) ?: emptyList())
}