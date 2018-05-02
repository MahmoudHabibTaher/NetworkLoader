package com.parent.domain.user

import android.util.Log
import com.parent.domain.auth.AuthDataSource
import com.parent.domain.common.prefs.Prefs
import com.parent.entities.InstitutionModel
import com.parent.entities.Language
import com.parent.entities.User
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 9/8/17.
 */
class AuthLocalDataSource(private val prefsManager: Prefs,
                          private val userDao: UserDao) : AuthDataSource {

    override fun getLanguage(): Language =
            Language(prefsManager.languageID, prefsManager.languageDisplay, prefsManager.language)


    override fun setLanguage(language: Language): Completable =
            Completable.create {
                prefsManager.language = language.code
                prefsManager.languageDisplay = language.displayName
                prefsManager.languageID = language.id
                if (!it.isDisposed) {
                    it.onComplete()
                }
            }


    override fun saveOneSignalToken(token: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun login(email: String, password: String): Single<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUser(userId: String): Single<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCurrentUser(): Single<User> =
            Single.just(prefsManager.currentUserId).flatMap {
                userDao.loadUser(it)
            }

    override fun setCurrentUser(user: User): Completable =
            Completable.fromAction {
                prefsManager.currentUserId = user.id ?: ""
            }

    override fun getCurrentInstitution(): Single<InstitutionModel> =
            getCurrentUser().flatMap {
                var currentInstitutionId = prefsManager.currentInstitutionId
                var institutionsList = it.institutions

                var currentInstitution = InstitutionModel()
                var found = false
                for (inst in institutionsList) {
                    if (inst.id.equals(currentInstitutionId)) {
                        currentInstitution = inst
                        found = true
                        break
                    }
                }
                if (!found) {
                    currentInstitution = institutionsList.firstOrNull() ?: InstitutionModel()
                }
                Single.just(currentInstitution)
            }

    override fun isCurrentInstituteSelected(): Single<Boolean> =
            Single.just(prefsManager.isCurrentInstituteSelected)

    override fun setCurrentInstitution(institutionId: String): Completable {
        prefsManager.isCurrentInstituteSelected = true
        return Completable.fromAction {
            prefsManager.currentInstitutionId = institutionId
        }
    }

    override fun deselectCurrentInstitution(): Completable {
        Log.e("deselectCurrentInst", "false")
        prefsManager.isCurrentInstituteSelected = false
        return Completable.complete()

    }

    override fun deleteCurrentUser(): Completable =
            Single.just(prefsManager.currentUserId).flatMapCompletable {
                userDao.deleteUser(it)
            }

    override fun saveUser(user: User): Completable =
            userDao.saveUser(user)

    override fun setAccessToken(token: String): Completable =
            Completable.create {
                prefsManager.accessToken = token

                if (!it.isDisposed) {
                    it.onComplete()
                }
            }

    override fun getAccessToken(): Single<String> =
            Single.fromCallable { prefsManager.accessToken }

    override fun setRefreshToken(refreshToken: String): Completable =
            Completable.create {
                prefsManager.refreshToken = refreshToken

                if (!it.isDisposed) {
                    it.onComplete()
                }
            }

    override fun getRefreshToken(): Single<String> =
            Single.fromCallable {
                prefsManager.refreshToken
            }

    override fun checkActivationLinkExpired(link: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun resetPassword(password: String, token: String): Single<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun requestNewResetPasswordLink(email: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun checkResetLinkExpired(link: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createPassword(password: String, confirmation_code: String): Single<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun forgetPassword(email: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun requestNewActivationLink(email: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun refreshAccessToken(refreshToken: String): Single<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setLastUserEmail(email: String): Completable =
            Completable.create {
                prefsManager.lastUserEmail = email

                if (!it.isDisposed) {
                    it.onComplete()
                }
            }

    override fun getLastUserEmail(): Single<String> =
            Single.fromCallable { prefsManager.lastUserEmail }


}