package com.parent.domain.main

import com.jakewharton.rxrelay2.BehaviorRelay
import com.parent.domain.newsfeed.INewPostObservable
import com.parent.domain.newsfeed.INewPostPublisher
import io.reactivex.Observable

/**
 * Created by mahmoud on 1/18/18.
 */
object InstitutionSelectedStateManager : IInstitutionSelectedPublisher, IInstitutionSelectedObservable {
    private val institutionSelectedObservable: BehaviorRelay<String> = BehaviorRelay.create()

    override fun notifyInstitutionSelected(id:String) {
        institutionSelectedObservable.accept(id)
    }

    override fun getObservable(): Observable<String> = institutionSelectedObservable
}