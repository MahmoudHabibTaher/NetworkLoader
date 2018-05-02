package com.parent.domain.auth

import com.parent.entities.InstitutionModel
import com.parent.entities.Language
import com.parent.entities.User
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 9/8/17.
 */
class AuthRepository(private val authRemoteDataSource: AuthDataSource,
                     private val authLocalDataSource: AuthDataSource) : AuthDataSource {
    override fun getLanguage(): Language =
        authLocalDataSource.getLanguage()


    override fun setLanguage(language: Language):Completable =
        authLocalDataSource.setLanguage(language)


    override fun login(email: String, password: String): Single<User> =
            authRemoteDataSource.login(email, password)
                    .doOnSuccess {
                        authLocalDataSource.saveUser(it).andThen(
                                authLocalDataSource.deselectCurrentInstitution()).andThen(
                                authLocalDataSource.setLanguage(it.language)
                        ).andThen(
                                authLocalDataSource.setCurrentUser(it).andThen(
                                        authLocalDataSource.setLastUserEmail(it.email)
                                )

                        ).blockingAwait()
                    }

    override fun saveOneSignalToken(token: String): Completable =
            authRemoteDataSource.saveOneSignalToken(token)

    override fun getUser(userId: String): Single<User> =
            authRemoteDataSource.getUser(userId)
                    .doOnSuccess {
                        authLocalDataSource.saveUser(it).andThen(authLocalDataSource.setCurrentUser(it)).blockingAwait()
                    }

    override fun getCurrentUser(): Single<User> =
            authLocalDataSource.getCurrentUser()

    override fun setCurrentUser(user: User): Completable =
            authLocalDataSource.setCurrentUser(user)


    override fun getCurrentInstitution(): Single<InstitutionModel> =
            authLocalDataSource.getCurrentInstitution()

    override fun isCurrentInstituteSelected(): Single<Boolean> =
            authLocalDataSource.isCurrentInstituteSelected()

    override fun deselectCurrentInstitution(): Completable =
            authLocalDataSource.deselectCurrentInstitution()

    override fun setCurrentInstitution(institutionId: String): Completable =
            authLocalDataSource.setCurrentInstitution(institutionId)

    override fun checkResetLinkExpired(link: String): Completable =
            authRemoteDataSource.checkResetLinkExpired(link)

    override fun checkActivationLinkExpired(link: String): Completable =
            authRemoteDataSource.checkActivationLinkExpired(link)

    override fun createPassword(password: String, confirmation_code: String): Single<User> =
            authRemoteDataSource.createPassword(password, confirmation_code)
                    .doOnSuccess {
                        authLocalDataSource.saveUser(it).andThen(
                                authLocalDataSource.deselectCurrentInstitution()).andThen(
                                authLocalDataSource.setCurrentUser(it)
                        ).blockingAwait()
                    }

    override fun resetPassword(password: String, token: String): Single<User> =
            authRemoteDataSource.resetPassword(password, token)
                    .doOnSuccess {
                        authLocalDataSource.saveUser(it).andThen(
                                authLocalDataSource.deselectCurrentInstitution()).andThen(
                                authLocalDataSource.setCurrentUser(it)
                        ).blockingAwait()
                    }

    override fun forgetPassword(email: String): Completable =
            authRemoteDataSource.forgetPassword(email)


    override fun requestNewResetPasswordLink(email: String): Completable =
            authRemoteDataSource.requestNewResetPasswordLink(email)

    override fun requestNewActivationLink(email: String): Completable =
            authRemoteDataSource.requestNewActivationLink(email)

    override fun setAccessToken(token: String): Completable =
            authLocalDataSource.setAccessToken(token)

    override fun getAccessToken(): Single<String> =
            authLocalDataSource.getAccessToken()

    override fun setRefreshToken(refreshToken: String): Completable =
            authLocalDataSource.setRefreshToken(refreshToken)

    override fun getRefreshToken(): Single<String> =
            authLocalDataSource.getRefreshToken()

    override fun refreshAccessToken(refreshToken: String): Single<User> =
            authRemoteDataSource.refreshAccessToken(refreshToken).doOnSuccess {
                authLocalDataSource.saveUser(it).andThen(
                        authLocalDataSource.deselectCurrentInstitution()).andThen(
                        authLocalDataSource.setCurrentUser(it).andThen(
                                authLocalDataSource.setLastUserEmail(it.email)
                        )

                ).blockingAwait()
            }

    override fun saveUser(user: User): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteCurrentUser(): Completable =
            authLocalDataSource.deleteCurrentUser()

    override fun setLastUserEmail(email: String): Completable =
            authLocalDataSource.setLastUserEmail(email)

    override fun getLastUserEmail(): Single<String> =
            authLocalDataSource.getLastUserEmail()


}