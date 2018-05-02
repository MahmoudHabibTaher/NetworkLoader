package com.parent.domain.overview

import com.parent.domain.base.BaseDataSource
import com.parent.entities.Status
import com.parent.entities.StatusData
import io.reactivex.Single

/**
 * Created by ahmedmahmoud on 2/17/18.
 */
interface StatusDataDataSource : BaseDataSource {
    fun loadInstituteStatusData(instituteId: String,statusCode: String): Single<List<StatusData>>

}