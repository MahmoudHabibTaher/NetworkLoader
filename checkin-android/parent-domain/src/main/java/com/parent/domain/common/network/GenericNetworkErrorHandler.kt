package com.parent.domain.common.network

import android.app.Application
import com.parent.domain.R
import com.parent.entities.BaseErrorResponse
import com.parent.entities.exceptions.*
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by mahmoud on 10/13/17.
 */
class GenericNetworkErrorHandler(private val application: Application) : NetworkErrorHandler {
    override fun handleError(throwable: Throwable): Throwable =
            when (throwable) {
                is UnknownHostException,
                is ConnectException -> handleNoConnectionException()
                is SocketTimeoutException,
                is SocketException -> handleConnectionTimeoutException()
                is HttpException -> handleHttpException(throwable)
                else -> throwable
            }

    private fun handleNoConnectionException(): Throwable = NoInternetConnectionException(application.getString(R.string.no_internet_connection_error))

    private fun handleConnectionTimeoutException(): Throwable = ConnectionTimeoutException(application.getString(R.string.connection_timeout_error))

    private fun handleHttpException(exception: HttpException): Throwable {
        val code = exception.code()
        return when (code) {
            ErrorConstants.NOT_AUTHENTICATED_401 -> {
                val errorResponse = exception.toErrorResponseModel<BaseErrorResponse<Any>>()
                NotAuthenticatedException(errorResponse?.message
                        ?: "")
            }
            ErrorConstants.REQUEST_TIMEOUT_408 -> {
                val errorResponse = exception.toErrorResponseModel<BaseErrorResponse<Any>>()
                TokenExpiredException(errorResponse?.message
                        ?: "")
            }
            ErrorConstants.OLD_TOKEN_ALREADY_REFRESHED_406 -> {
                TokenAlreadyRefreshedException()
            }
            ErrorConstants.GONE_410 -> {
                val errorResponse = exception.toErrorResponseModel<BaseErrorResponse<Any>>()
                PermissionsChangedException(errorResponse?.message ?: "")
            }
            else -> exception
        }
    }
}