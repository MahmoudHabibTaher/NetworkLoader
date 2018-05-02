package com.parent.domain.realm.base

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.realm.RealmModel
import io.realm.RealmQuery

/**
 * Created by mahmoud on 10/16/17.
 */
interface RealmDBHelper<T : RealmModel> {
    fun save(value: T): Completable

    fun query(query: (RealmQuery<T>) -> Unit = { }): Observable<List<T>>

    fun queryFirst(query: (RealmQuery<T>) -> Unit = { }): Single<T>

    fun delete(value: T): Completable

    fun delete(list: List<T>): Completable

    fun delete(query: (RealmQuery<T>) -> Unit = { }): Completable

    fun deleteAll(): Completable

    fun observeChanges(query: (RealmQuery<T>) -> Unit = { }): Flowable<List<T>>
}