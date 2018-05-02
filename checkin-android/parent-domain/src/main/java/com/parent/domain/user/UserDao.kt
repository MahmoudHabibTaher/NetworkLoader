package com.parent.domain.user

import com.parent.entities.User
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 10/5/17.
 */
interface UserDao {
    fun saveUser(user: User): Completable

    fun loadUser(id: String): Single<User>

    fun loadUsers(): Single<List<User>>

    fun deleteUser(userId: String): Completable
}