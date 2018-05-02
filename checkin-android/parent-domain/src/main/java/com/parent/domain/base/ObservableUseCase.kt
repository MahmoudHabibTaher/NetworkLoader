package com.parent.domain.base

import io.reactivex.Observable

/**
 * Created by mahmoud on 9/11/17.
 */
interface ObservableUseCase<T> {
    fun getObservable(params: Params = emptyParams()): Observable<T>
}