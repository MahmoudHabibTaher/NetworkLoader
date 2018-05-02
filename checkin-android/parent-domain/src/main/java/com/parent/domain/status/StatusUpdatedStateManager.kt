package com.parent.domain.status

import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable

/**
 * Created by mahmoud on 1/18/18.
 */
object StatusUpdatedStateManager : IStatusUpdatedPublisher, IStatusUpdatedObservable {

    private val currentChildNumberUpdateObservable: BehaviorRelay<Boolean> = BehaviorRelay.create()

    override fun notifyStatusUpdated() =
            currentChildNumberUpdateObservable.accept(true)

    override fun subscribeStatusUpdated(): Observable<Boolean> =
            currentChildNumberUpdateObservable

}