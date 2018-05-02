package com.parent.domain.children.contacts

import io.reactivex.disposables.Disposable

/**
 * Created by mahmoud on 1/4/18.
 */
interface IChildContactStateObservable {
    fun subscribeChildContactAddedSuccefully(onChildStateUpdated: (Boolean) -> Unit): Disposable
}