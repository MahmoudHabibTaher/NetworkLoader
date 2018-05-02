package com.parent.domain.status

import io.reactivex.Observable

/**
 * Created by mahmoud on 1/18/18.
 */
interface IStatusUpdatedObservable {
    fun subscribeStatusUpdated(): Observable<Boolean>
}