package com.bigo.networkloader.http

import com.bigo.networkloader.http.builder.ConnectionBuilder
import com.bigo.networkloader.http.exceptions.HttpException
import io.reactivex.Single
import javax.net.ssl.HttpsURLConnection

open class HttpClientImpl(private val connectionBuilder: ConnectionBuilder) {
    companion object {
        const val DEFAULT_READ_TIME_OUT = 10000 // 10 Seconds
        const val DEFAULT_CONNECTION_TIME_OUT = 10000 // 10 Seconds
    }

    var readTimeout = DEFAULT_READ_TIME_OUT
    var connectTimeout = DEFAULT_CONNECTION_TIME_OUT

    fun <T> get(request: HttpRequest<T>): Single<HttpResponse<T>> =
        Single.create { emitter ->
            var connection: HttpsURLConnection? = null

            emitter.setCancellable {
                connection?.apply(::closeConnection)
            }

            try {
                connection = connectionBuilder.build(request.url, readTimeout, connectTimeout)

                val response = connection?.run {
                    connect()

                    if (responseCode != HttpsURLConnection.HTTP_OK) {
                        throw HttpException(responseCode, responseMessage)
                    }

                    val data = request.parser?.parse(inputStream)

                    HttpResponse(request.url, responseCode, data)
                }

                response?.let {
                    if (!emitter.isDisposed) {
                        emitter.onSuccess(it)
                    }
                }

            } catch (ex: Exception) {
                if (!emitter.isDisposed) {
                    emitter.onError(ex)
                }
            } finally {
                connection?.apply(::closeConnection)
            }
        }

    private fun closeConnection(connection: HttpsURLConnection) {
        connection.inputStream?.close()
        connection.disconnect()
    }
}