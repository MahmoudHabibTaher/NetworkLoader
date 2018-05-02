package com.parent.domain.main

import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable

/**
 * Created by mahmoud on 1/18/18.
 */
object MainActivityStateManager : MainActivityStatePublisher, MainActivityStateObservable {


    private val mainActivityStateObservable: BehaviorRelay<Boolean> = BehaviorRelay.create()

    override fun subscribeChildrenAvailableState(): Observable<Boolean> =
            mainActivityStateObservable

    override fun notifyChildrenAvailableState(state: Boolean) {
        mainActivityStateObservable.accept(state)
    }

}