package com.bigo.networkloader

import com.bigo.networkloader.http.HttpClient
import com.bigo.networkloader.http.HttpRequest
import com.bigo.networkloader.parser.ResponseParser
import io.reactivex.Single

class NetworkLoader(private val httpClient: HttpClient) {
    fun <T> load(url: String, parser: ResponseParser<T>): Single<T> =
        httpClient.get(buildRequest(url, parser)).flatMap {
            Single.just(it.data)
        }

    private fun <T> buildRequest(url: String, parser: ResponseParser<T>) =
        HttpRequest.Builder<T>()
            .url(url)
            .parser(parser)
            .build()
}