package com.bigo.networkloader.http.builder

import javax.net.ssl.HttpsURLConnection

interface ConnectionBuilder {
    fun build(url: String, readTimeout: Int, connectTimeout: Int): HttpsURLConnection?
}