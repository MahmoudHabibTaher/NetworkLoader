package com.parent.domain.overview

import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.base.mappers.mapFromWith
import com.parent.domain.common.data.source.remote.BaseRemoteDataSource
import com.parent.entities.StatusData
import com.parent.entities.StatusDataRemote
import io.reactivex.Single

/**
 * Created by ahmedmahmoud on 2/17/18.
 */
class StatusDataRemoteDataSource(private val api: StatusesApi,
                                 private val modelMapper: ModelMapper<StatusDataRemote, StatusData>) : StatusDataDataSource, BaseRemoteDataSource() {
    override fun loadInstituteStatusData(instituteId: String, statusCode: String): Single<List<StatusData>> =
            callSingle(api.getInstituteStatusData(instituteId, statusCode).flatMap {
                mapStatuses(it.data)
            })


    override fun invalidateCache() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private fun mapStatuses(data: List<StatusDataRemote>?) =
            Single.just(data?.mapFromWith(modelMapper) ?: emptyList())
}