package com.parent.domain.children

import com.parent.entities.ChildState
import io.reactivex.disposables.Disposable

/**
 * Created by mahmoud on 1/4/18.
 */
interface IChildStateObservable {
    fun subscribeChildState(onChildStateUpdated: (ChildState) -> Unit): Disposable
}