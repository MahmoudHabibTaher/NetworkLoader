package com.parent.domain.main

import io.reactivex.Observable

/**
 * Created by mahmoud on 1/18/18.
 */
interface IInstitutionSelectedObservable {
    fun getObservable(): Observable<String>
}