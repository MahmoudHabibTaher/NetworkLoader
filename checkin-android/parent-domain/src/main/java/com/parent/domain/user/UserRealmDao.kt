package com.parent.domain.user

import com.parent.entities.User
import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.realm.base.RealmDBHelper
import com.parent.domain.realm.entities.UserRealm
import com.vicpin.krealmextensions.delete
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 10/3/17.
 */
class UserRealmDao(private val realmDBHelper: RealmDBHelper<UserRealm>,
                   private val modelMapper: ModelMapper<UserRealm, User>) : UserDao {
    override fun saveUser(user: User): Completable =
            realmDBHelper.save(modelMapper.mapTo(user))

    override fun loadUser(id: String): Single<User> =
            realmDBHelper.queryFirst { query -> query.equalTo("id", id) }
                    .flatMap { mapUser(it) }

    override fun loadUsers(): Single<List<User>> =
            realmDBHelper.query().toList().flatMap { mapUsers(it.flatten()) }

    override fun deleteUser(userId: String): Completable =
            Completable.fromAction {
                UserRealm().delete { it.equalTo("id", userId) }
            }

    private fun mapUser(userRealm: UserRealm) =
            Single.just(modelMapper.mapFrom(userRealm))

    private fun mapUsers(userRealmList: List<UserRealm>) =
            Single.fromCallable { userRealmList.map { modelMapper.mapFrom(it) } }
}