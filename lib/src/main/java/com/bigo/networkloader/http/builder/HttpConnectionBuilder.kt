package com.bigo.networkloader.http.builder

import com.bigo.networkloader.http.HttpMethods
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class HttpConnectionBuilder : ConnectionBuilder {
    override fun build(url: String, readTimeout: Int, connectTimeout: Int): HttpsURLConnection? =
        (URL(url).openConnection() as? HttpsURLConnection)?.apply {
            this.readTimeout = readTimeout
            this.connectTimeout = connectTimeout
            this.requestMethod = HttpMethods.GET
            this.doInput = true
        }
}