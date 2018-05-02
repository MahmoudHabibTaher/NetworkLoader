package com.parent.domain.common.network

import retrofit2.Retrofit

/**
 * Created by mahmoud on 9/8/17.
 */
class NetworkManager(private val retrofit: Retrofit) {
    fun <T> createService(clazz: Class<T>) = retrofit.create(clazz)
}