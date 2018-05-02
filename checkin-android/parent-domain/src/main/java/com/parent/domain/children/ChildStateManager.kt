package com.parent.domain.children

import com.jakewharton.rxrelay2.BehaviorRelay
import com.parent.entities.ChildState
import io.reactivex.disposables.Disposable

/**
 * Created by mahmoud on 1/4/18.
 */
object ChildStateManager : IChildStatePublisher, IChildStateObservable {

    private val childStateObservable: BehaviorRelay<ChildState> = BehaviorRelay.create()

    override fun notifyState(state: ChildState) {
        childStateObservable.accept(state)
    }

    override fun subscribeChildState(onChildStateUpdated: (ChildState) -> Unit): Disposable =
            childStateObservable.subscribe {
                onChildStateUpdated(it)
            }
}