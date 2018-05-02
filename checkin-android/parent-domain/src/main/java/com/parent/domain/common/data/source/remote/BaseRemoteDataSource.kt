package com.parent.domain.common.data.source.remote

import com.google.gson.JsonSyntaxException
import com.parent.domain.common.network.ErrorConstants
import com.parent.domain.common.network.toErrorResponseModel
import com.parent.entities.BaseErrorResponse
import com.parent.entities.ValidationErrorsResponse
import com.parent.entities.exceptions.*
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.HttpException

/**
 * Created by mahmoud on 12/30/17.
 */
abstract class BaseRemoteDataSource {
    companion object {
        private const val SERVER_ERROR = "An error has occurred, please try again"
    }

    fun <T> callSingle(single: Single<T>, onError: (Throwable) -> Boolean = { false },
                       handleError: (Throwable) -> Single<T> = { Single.error(it) }) =
            single.onErrorResumeNext {
                if (onError(it)) {
                    handleError(it)
                } else {
                    handleErrorInternalSingle(it)
                }
            }

    fun callCompletable(completable: Completable, onError: (Throwable) -> Boolean = { false },
                        handleError: (Throwable) -> Completable = { Completable.error(it) }) =
            completable.onErrorResumeNext {
                if (onError(it)) {
                    handleError(it)
                } else {
                    handleErrorInternalCompletable(it)
                }
            }

    private fun <T> handleErrorInternalSingle(throwable: Throwable): Single<T> =
            Single.error(handleErrorInternal(throwable))

    private fun handleErrorInternalCompletable(throwable: Throwable): Completable =
            Completable.error(handleErrorInternal(throwable))

    private fun handleErrorInternal(throwable: Throwable): Throwable {
        val error: Throwable

        error = if (throwable is HttpException) {
            val code = throwable.code()

            when (code) {
                ErrorConstants.NOT_AUTHORIZED_403 -> NotAuthorizedException(getErrorMessage(throwable))
                ErrorConstants.NOT_FOUND_404 -> ItemNotFoundException(getErrorMessage(throwable))
                ErrorConstants.INPUT_VALIDATION_400 -> ValidationErrorsException("", getValidationErrors(throwable))
                else -> throwable
            }
        } else {
            when (throwable) {
                is JsonSyntaxException,
                is ParseResponseException -> UnknownNetworkError()
                else -> throwable
            }
        }

        return error
    }

    private fun getErrorMessage(error: HttpException) =
            error.toErrorResponseModel<BaseErrorResponse<Any>>()?.message ?: SERVER_ERROR

    private fun getValidationErrors(error: HttpException) =
            error.toErrorResponseModel<ValidationErrorsResponse>()?.data ?: emptyList()
}