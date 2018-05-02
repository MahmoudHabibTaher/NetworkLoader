package com.parent.domain.common.network

import com.parent.domain.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * Created by mahmoud on 9/22/17.
 */
object OkHttpClientHelper {
    fun buildClient(connectionTimeOut: Long,
                    readTimeOut: Long,
                    writeTimeOut: Long,
                    headersProvider: HeadersProvider?,
                    interceptor: Interceptor) =
            OkHttpClient.Builder()
                    .apply {
                        connectTimeout(connectionTimeOut, TimeUnit.SECONDS)
                        readTimeout(readTimeOut, TimeUnit.SECONDS)
                        writeTimeout(writeTimeOut, TimeUnit.SECONDS)
                        addInterceptor {
                            var request = it.request()
                            headersProvider?.let {
                                val builder = request.newBuilder()
                                val headers = it.getHeaders()
                                for ((key, value) in headers) {
                                    builder.addHeader(key, value)
                                }
                                request = builder.build()
                            }

                            return@addInterceptor it.proceed(request)
                        }
                        @Suppress("ConstantConditionIf")
                        if (BuildConfig.HTTP_LOGGING_ENABLED) {
                            addInterceptor(interceptor)
                        }
                    }
                    .build()
}