package com.parent.domain.overview

import com.parent.domain.base.BaseDataSource
import com.parent.entities.Status
import io.reactivex.Single

/**
 * Created by ahmedmahmoud on 2/17/18.
 */
interface StatusDataSource : BaseDataSource {
    fun loadDashboadStatus(instituteId: String): Single<List<Status>>

}