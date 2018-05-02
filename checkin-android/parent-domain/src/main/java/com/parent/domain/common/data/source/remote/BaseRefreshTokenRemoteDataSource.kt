package com.parent.domain.common.data.source.remote

import com.parent.domain.auth.AuthDataSource
import com.parent.domain.common.network.ErrorConstants
import io.reactivex.Observable
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import retrofit2.HttpException
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by mahmoud on 9/14/17.
 */
abstract class BaseRefreshTokenRemoteDataSource : AnkoLogger {
    abstract val authDataSource: AuthDataSource
    private var refreshAccessTokenAttempts = AtomicInteger(0)

    fun <T> attachErrorHandling(observable: Observable<T>): Observable<T> =
            observable.retryWhen { errorObservable ->
                errorObservable.flatMap { error ->
                    debug("onError ", error)
                    if (error is HttpException) {
                        val code = error.code()
                        if (code == ErrorConstants.NOT_AUTHENTICATED_401) {
                            if (shouldRefreshAccessToken()) {
                                debug("Should refresh access token : TRUE")
                                refreshAccessTokenAttempts.incrementAndGet()
                                return@flatMap refreshAccessToken().toObservable().flatMap {
                                    Observable.just(true)
                                }
                            } else {
                                refreshAccessTokenAttempts.set(0)
                                debug("Should refresh access token : FALSE")
                                return@flatMap Observable.error<Throwable>(error)
                            }
                        } else {
                            return@flatMap Observable.error<Throwable>(error)
                        }
                    }

                    return@flatMap Observable.error<Throwable>(error)
                }
            }

    private fun shouldRefreshAccessToken() = refreshAccessTokenAttempts.get() == 0

    private fun refreshAccessToken() = authDataSource.getRefreshToken().flatMap {
        authDataSource.refreshAccessToken(it).doOnSuccess {
            debug("Refresh access token success $it")
        }.doOnError {
            debug("Refresh access token failed", it)
        }
    }
}