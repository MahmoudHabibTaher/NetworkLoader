package com.parent.domain.auth

import com.parent.domain.base.CompletableUseCase
import com.parent.entities.InstitutionModel
import com.parent.entities.Language
import com.parent.entities.User
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 10/27/17.
 */
class SessionManager(private val authDataSource: AuthDataSource,
                     private val refreshToken: CompletableUseCase) : ISessionManager {
    override fun getLanguage(): Language =
        authDataSource.getLanguage()


    override fun setLanguage(language: Language) {
            authDataSource.setLanguage(language)
    }

    override fun login(email: String, password: String): Completable =
            authDataSource.login(email, password).toCompletable()

    override fun refreshAccessToken(): Completable =
            refreshToken.getCompletable()

    override fun isUserLoggedIn(): Single<Boolean> =
            authDataSource.getAccessToken().flatMap {
                Single.just(it.isNotBlank())
            }

    override fun getLoggedInUser(): Single<User> =
            authDataSource.getCurrentUser()

    override fun getCurrentInstitute(): Single<InstitutionModel> =
            authDataSource.getCurrentInstitution()

    override fun isCurrentInstituteSelected(): Single<Boolean> =
            authDataSource.isCurrentInstituteSelected()

    override fun setCurrentInstitute(institutionId: String): Completable =
            authDataSource.setCurrentInstitution(institutionId)

    override fun refreshUserInfo(): Completable =
            getLoggedInUser().flatMapCompletable {
                authDataSource.getUser(it.id ?: "").toCompletable()
            }

    override fun isCompanyAdmin(): Single<Boolean> =
            authDataSource.getCurrentUser().flatMap {
                Single.just(it.role.id == "1" || it.role.id == "2")
            }

    override fun logout(): Completable =
            authDataSource.deleteCurrentUser().andThen(authDataSource.setAccessToken(""))
}