package com.parent.domain.auth

import com.parent.entities.InstitutionModel
import com.parent.entities.Language
import com.parent.entities.User
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 10/27/17.
 */
interface ISessionManager {
    fun login(email: String, password: String): Completable

    fun refreshAccessToken(): Completable

    fun isUserLoggedIn(): Single<Boolean>

    fun getLoggedInUser(): Single<User>

    fun getCurrentInstitute(): Single<InstitutionModel>

    fun isCurrentInstituteSelected(): Single<Boolean>

    fun setCurrentInstitute(institutionId: String): Completable

    fun refreshUserInfo(): Completable

    fun isCompanyAdmin(): Single<Boolean>

    fun logout(): Completable

    fun getLanguage(): Language

    fun setLanguage(Language: Language)
}