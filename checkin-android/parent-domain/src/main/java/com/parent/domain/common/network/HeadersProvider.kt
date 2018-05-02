package com.parent.domain.common.network

/**
 * Created by mahmoud on 9/11/17.
 */
interface HeadersProvider {
    fun getHeaders(): Map<String, String>

    fun getHeader(name: String): String
}