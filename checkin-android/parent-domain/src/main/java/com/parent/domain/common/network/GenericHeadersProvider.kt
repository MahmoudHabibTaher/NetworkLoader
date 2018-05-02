package com.parent.domain.common.network

import com.parent.domain.common.prefs.Prefs

/**
 * Created by mahmoud on 9/11/17.
 */
class GenericHeadersProvider(private val prefsManager: Prefs) : HeadersProvider {

    companion object {
        const val HEADER_AUTH = "Authorization"
        const val HEADER_LANGUAGE = "Accept-Language"
    }

    override fun getHeaders(): Map<String, String> {
        val map = mutableMapOf<String, String>()
        if (prefsManager.hasAccessToken) {
            map[HEADER_AUTH] = "Bearer " + prefsManager.accessToken
        }

        map[HEADER_LANGUAGE] = prefsManager.language.toLowerCase()
        return map
    }

    override fun getHeader(name: String): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}