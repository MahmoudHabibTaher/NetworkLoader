package com.parent.domain.children.contacts

import android.util.Log
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.disposables.Disposable

/**
 * Created by mahmoud on 1/4/18.
 */
object ChildContactStateManager : IChildContactStatePublisher, IChildContactStateObservable {

    private val childContactStateObservable: BehaviorRelay<Boolean> = BehaviorRelay.create()

    override fun notifyChildContactAddedSuccefully(state: Boolean) {
        childContactStateObservable.accept(state)
    }

    override fun notifyChildContactEditedSuccefully(state: Boolean) {
        childContactStateObservable.accept(state)
    }

    override fun subscribeChildContactAddedSuccefully(onChildContactStateUpdated: (Boolean) -> Unit): Disposable {
        return childContactStateObservable.subscribe {
            onChildContactStateUpdated(it)
        }
    }
}