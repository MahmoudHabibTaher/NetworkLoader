package com.parent.domain.auth

import com.parent.entities.InstitutionModel
import com.parent.entities.Language
import com.parent.entities.User
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 9/8/17.
 */
interface AuthDataSource {
    fun login(email: String, password: String): Single<User>

    fun saveOneSignalToken(token: String): Completable

    fun saveUser(user: User): Completable

    fun getUser(userId: String): Single<User>

    fun getCurrentUser(): Single<User>

    fun setCurrentUser(user: User): Completable

    fun getCurrentInstitution(): Single<InstitutionModel>

    fun isCurrentInstituteSelected(): Single<Boolean>

    fun deselectCurrentInstitution(): Completable

    fun setCurrentInstitution(institutionId: String): Completable

    fun checkActivationLinkExpired(link: String): Completable

    fun checkResetLinkExpired(link: String): Completable

    fun createPassword(password: String, confirmation_code: String): Single<User>

    fun resetPassword(password: String, token: String): Single<User>

    fun forgetPassword(email: String): Completable

    fun requestNewResetPasswordLink(email: String): Completable

    fun requestNewActivationLink(email: String): Completable

    fun setAccessToken(token: String): Completable

    fun getAccessToken(): Single<String>

    fun setRefreshToken(refreshToken: String): Completable

    fun getRefreshToken(): Single<String>

    fun refreshAccessToken(refreshToken: String): Single<User>

    fun deleteCurrentUser(): Completable

    fun setLastUserEmail(email: String): Completable

    fun getLastUserEmail(): Single<String>

    fun getLanguage(): Language

    fun setLanguage(Language: Language):Completable
}