package com.parent.domain.user

import com.parent.domain.realm.base.RealmDBHelper
import com.parent.domain.realm.entities.UserRealm
import com.vicpin.krealmextensions.*
import com.vicpin.krealmextensions.rx2.queryAsFlowable
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.realm.RealmQuery

/**
 * Created by mahmoud on 10/16/17.
 */
class UserRealmDBHelper : RealmDBHelper<UserRealm> {
    override fun save(value: UserRealm): Completable =
            Completable.fromRunnable {
                value.save()
            }

    override fun query(query: (RealmQuery<UserRealm>) -> Unit): Observable<List<UserRealm>> =
            Observable.just(UserRealm().query(query = query))

    override fun queryFirst(query: (RealmQuery<UserRealm>) -> Unit): Single<UserRealm> =
            Single.just(UserRealm().queryFirst(query) ?: UserRealm())

    override fun delete(value: UserRealm): Completable =
            Completable.fromAction {
                UserRealm().delete { it.equalTo("id", value.id) }
            }

    override fun delete(list: List<UserRealm>): Completable =
            Completable.fromAction {
                list.forEach { user ->
                    UserRealm().delete { it.equalTo("id", user.id) }
                }
            }

    override fun delete(query: (RealmQuery<UserRealm>) -> Unit): Completable =
            Completable.fromAction {
                UserRealm().delete(query)
            }

    override fun deleteAll(): Completable =
            Completable.fromAction {
                UserRealm().deleteAll()
            }

    override fun observeChanges(query: (RealmQuery<UserRealm>) -> Unit): Flowable<List<UserRealm>> =
            UserRealm().queryAsFlowable(query)
}