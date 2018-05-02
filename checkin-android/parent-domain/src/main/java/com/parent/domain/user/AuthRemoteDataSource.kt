package com.parent.domain.user

import com.parent.domain.auth.AuthDataSource
import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.base.mappers.mapFromWith
import com.parent.domain.common.data.source.remote.BaseRemoteDataSource
import com.parent.domain.common.network.ErrorConstants
import com.parent.domain.common.network.toErrorResponseModel
import com.parent.entities.*
import com.parent.entities.exceptions.*
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.HttpException

/**
 * Created by mahmoud on 9/8/17.
 */
class AuthRemoteDataSource(private val authApi: AuthApi,
                           private val userRemoteModelMapper: ModelMapper<LoginData, User>,
                           private val userInfoRemoteModelMapper: ModelMapper<UserRemote, User>) : AuthDataSource, BaseRemoteDataSource() {
    override fun getLanguage(): Language =
        Language()


    override fun setLanguage(Language: Language):Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun deselectCurrentInstitution(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isCurrentInstituteSelected(): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCurrentInstitution(): Single<InstitutionModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setCurrentInstitution(institutionId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun login(email: String, password: String): Single<User> =
            authApi.login(LoginRequest(email, password))
                    .flatMap {
                        val data = it.data
                        if (data != null) {
                            mapUserData(data).flatMap {
                                if (it.institutions.isEmpty()) {
                                    Single.error(ParseResponseException())
                                } else {
                                    Single.just(it)
                                }
                            }
                        } else {
                            Single.error(ParseResponseException())
                        }
                    }
                    .onErrorResumeNext {
                        if (it is HttpException) {
                            val code = it.code()
                            val errorResponse = it.toErrorResponseModel<BaseErrorResponse<Any>>()
                            when (code) {
                                ErrorConstants.INPUT_VALIDATION_400,
                                ErrorConstants.NOT_FOUND_404 ->
                                    Single.error(InvalidLoginException(errorResponse?.message))
                                ErrorConstants.NOT_AUTHORIZED_403 ->
                                    Single.error(AccountNotActiveException(errorResponse?.message))
                                else -> Single.error(it)
                            }
                        } else {
                            Single.error(it)
                        }
                    }

    override fun saveOneSignalToken(token: String): Completable =
            authApi.saveOneSignalToken(SavePushNotificationRequest(token)).onErrorResumeNext {
                if (it is HttpException) {
                    val code = it.code()
                    val errorResponse = it.toErrorResponseModel<BaseErrorResponse<Any>>()
                    when (code) {
                        ErrorConstants.INPUT_VALIDATION_400 -> {
                            Completable.error(InvalidToken(errorResponse?.message
                                    ?: ""))
                        }
                        ErrorConstants.NOT_AUTHORIZED_403 -> {
                            Completable.error(AccountNotActiveException(errorResponse?.message
                                    ?: ""))
                        }
                        else -> Completable.error(it)
                    }
                } else {
                    Completable.error(it)
                }
            }

    override fun getUser(userId: String): Single<User> =
            callSingle(authApi.getUser().flatMap {
                val user = it.data?.user
                if (user != null) {
                    Single.just(user mapFromWith userInfoRemoteModelMapper)
                } else {
                    Single.error(ParseResponseException())
                }
            })

    override fun getCurrentUser(): Single<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setCurrentUser(user: User): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun checkResetLinkExpired(link: String): Completable =
            authApi.checkResetLink(link).onErrorResumeNext {
                if (it is HttpException) {
                    val code = it.code()
                    val errorResponse = it.toErrorResponseModel<BaseErrorResponse<List<String>>>()
                    when (code) {
                        ErrorConstants.INPUT_VALIDATION_400 -> {
                            Completable.error(InvalidUserException(errorResponse?.message
                                    ?: ""))
                        }
                        ErrorConstants.NOT_AUTHORIZED_403 -> {
                            Completable.error(ResetLinkExpiredException(errorResponse?.message
                                    ?: "", if (errorResponse?.data?.isNotEmpty() == true) {
                                errorResponse?.data?.get(0) ?: ""
                            } else {
                                ""
                            }))
                        }
                        ErrorConstants.NOT_FOUND_404 -> {
                            Completable.error(NoSuchElementException(errorResponse?.message ?: ""))
                        }
                        ErrorConstants.BUSINESS_VALIDATION_ERROR_409 ->
                            Completable.error(ResetLinkInvalidException(errorResponse?.message
                                    ?: "", if (errorResponse?.data?.isNotEmpty() == true) {
                                errorResponse?.data?.get(0) ?: ""
                            } else {
                                ""
                            }))
                        else -> Completable.error(it)
                    }
                } else {
                    Completable.error(it)
                }
            }

    override fun checkActivationLinkExpired(link: String): Completable =
            authApi.checkActivationLinkExpired(link).onErrorResumeNext {
                if (it is HttpException) {
                    val code = it.code()
                    val errorResponse = it.toErrorResponseModel<BaseErrorResponse<String>>()
                    when (code) {
                        ErrorConstants.INPUT_VALIDATION_400 ->
                            Completable.error(InvalidActivationLinkException(errorResponse?.data
                                    ?: ""))
                        ErrorConstants.NOT_AUTHORIZED_403 ->
                            Completable.error(ActivationLinkExpiredException(errorResponse?.data
                                    ?: ""))
                        ErrorConstants.BUSINESS_VALIDATION_ERROR_409 ->
                            Completable.error(AccountAlreadyActivatedException(errorResponse?.message
                                    ?: ""))
                        else -> Completable.error(it)
                    }
                } else {
                    Completable.error(it)
                }
            }


    override fun createPassword(password: String, confirmation_code: String): Single<User> =
            authApi.createPassword(CreatePasswordRequest(password, confirmation_code)).flatMap {
                val data = it.data
                if (data != null) {
                    mapUserData(data)
                } else {
                    Single.error(ParseResponseException())
                }
            }.onErrorResumeNext {
                if (it is HttpException) {
                    val code = it.code()
                    val errorResponse = it.toErrorResponseModel<BaseErrorResponse<Any>>()
                    when (code) {
                        ErrorConstants.NOT_FOUND_404 ->
                            Single.error(InvalidConfirmationCodeException(errorResponse?.message))
                        ErrorConstants.INPUT_VALIDATION_400 ->
                            Single.error(AccountAlreadyActivatedException(errorResponse?.message))
                        else -> Single.error(it)
                    }

                } else {
                    Single.error(it)
                }
            }

    override fun resetPassword(password: String, token: String): Single<User> =
            authApi.resetPassword(ResetPasswordRequest(password, token)).flatMap {
                val data = it.data
                if (data != null) {
                    mapUserData(data)
                } else {
                    Single.error(ParseResponseException())
                }
            }.onErrorResumeNext {
                if (it is HttpException) {
                    val code = it.code()
                    val errorResponse = it.toErrorResponseModel<BaseErrorResponse<String>>()
                    when (code) {
                        ErrorConstants.NOT_FOUND_404 ->
                            Single.error(NoSuchElementException(errorResponse?.message))
                        ErrorConstants.INPUT_VALIDATION_400 -> {
                            Single.error(InvalidUserException(errorResponse?.message
                                    ?: ""))
                        }
                        ErrorConstants.NOT_AUTHORIZED_403 -> {
                            Single.error(ResetLinkExpiredException(errorResponse?.message
                                    ?: "", errorResponse?.data ?: ""))
                        }
                        ErrorConstants.BUSINESS_VALIDATION_ERROR_409 -> {
                            Single.error(ResetLinkInvalidException(errorResponse?.message
                                    ?: "", errorResponse?.data ?: ""))
                        }
                        else -> Single.error(it)
                    }
                } else {
                    Single.error(it)
                }
            }

    override fun forgetPassword(email: String): Completable =
            authApi.forgetPassword(ForgetPassRequest(email)).onErrorResumeNext {
                if (it is HttpException) {
                    val code = it.code()
                    val errorResponse = it.toErrorResponseModel<BaseErrorResponse<Any>>()
                    when (code) {
                        ErrorConstants.NOT_FOUND_404 ->
                            Completable.error(EmailNotRegisteredException(errorResponse?.message
                                    ?: ""))
                        ErrorConstants.NOT_AUTHORIZED_403 ->
                            Completable.error(AccountNotActiveException(errorResponse?.message
                                    ?: ""))
                        else -> Completable.error(it)
                    }
                } else {
                    Completable.error(it)
                }
            }

    override fun requestNewResetPasswordLink(email: String): Completable =
            authApi.requestNewResetPasswordLink(RequestResetPasswordLinkRequest(email)).onErrorResumeNext {
                if (it is HttpException) {
                    val code = it.code()
                    val errorResponse = it.toErrorResponseModel<BaseErrorResponse<Any>>()
                    when (code) {
                        ErrorConstants.INPUT_VALIDATION_400 ->
                            Completable.error(EmailNotRegisteredException(errorResponse?.message
                                    ?: ""))
                        ErrorConstants.NOT_AUTHORIZED_403 ->
                            Completable.error(AccountNotActiveException(errorResponse?.message
                                    ?: ""))
                        else -> Completable.error(it)
                    }
                } else {
                    Completable.error(it)
                }
            }

    override fun requestNewActivationLink(email: String): Completable =
            authApi.requestNewActivationLink(email).onErrorResumeNext {
                if (it is HttpException) {
                    val code = it.code()
                    val errorResponse = it.toErrorResponseModel<BaseErrorResponse<Any>>()
                    when (code) {
                        ErrorConstants.INPUT_VALIDATION_400,
                        ErrorConstants.NOT_AUTHORIZED_403 ->
                            Completable.error(InvalidEmailException(errorResponse?.message
                                    ?: ""))
                        ErrorConstants.BUSINESS_VALIDATION_ERROR_409 ->
                            Completable.error(AccountAlreadyActiveException(errorResponse?.message
                                    ?: ""))
                        else -> Completable.error(it)
                    }
                } else {
                    Completable.error(it)
                }
            }

    override fun setAccessToken(token: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAccessToken(): Single<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setRefreshToken(refreshToken: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRefreshToken(): Single<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveUser(user: User): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun refreshAccessToken(refreshToken: String): Single<User> =
            authApi.refreshAccessToken()
                    .flatMap {
                        val data = it.data
                        if (data != null) {
                            mapUserData(data).flatMap {
                                if (it.institutions.isEmpty()) {
                                    Single.error(ParseResponseException())
                                } else {
                                    Single.just(it)
                                }
                            }
                        } else {
                            Single.error(ParseResponseException())
                        }
                    }.onErrorResumeNext {
                        if (it is TokenExpiredException) {
                            Single.error(NotAuthenticatedException(it.message ?: ""))
                        } else {
                            Single.error(it)
                        }
                    }

    override fun deleteCurrentUser(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun mapUserData(loginData: LoginData) =
            Single.fromCallable { userRemoteModelMapper.mapFrom(loginData) }
    override fun setLastUserEmail(email: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLastUserEmail(): Single<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}