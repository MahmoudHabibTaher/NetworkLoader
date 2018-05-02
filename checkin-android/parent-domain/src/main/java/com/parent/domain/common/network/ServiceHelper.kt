package com.parent.domain.common.network

import retrofit2.Retrofit

/**
 * Created by mahmoud on 9/22/17.
 */
object ServiceHelper {
    inline fun <reified T> createService(retrofit: Retrofit): T = retrofit.create(T::class.java)
}