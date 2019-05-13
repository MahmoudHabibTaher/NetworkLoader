package com.bigo.networkloader

import com.bigo.networkloader.cache.Cache
import com.bigo.networkloader.http.HttpClient
import com.bigo.networkloader.http.HttpRequest
import com.bigo.networkloader.http.HttpResponse
import com.bigo.networkloader.parser.ResponseParser
import io.reactivex.Single

class NetworkLoader(
    private val httpClient: HttpClient,
    private val cache: Cache<String, HttpResponse<*>>
) {
    fun <T> load(url: String, parser: ResponseParser<T>): Single<T> =
        if (cache.has(url)) {
            loadFromCache(url)
        } else {
            httpClient.get(buildRequest(url, parser))
                .doOnSuccess { response ->
                    addToCache(url, response)
                }
                .flatMap {
                    Single.just(it.data)
                }
        }

    private fun <T> buildRequest(url: String, parser: ResponseParser<T>) =
        HttpRequest.Builder<T>()
            .url(url)
            .parser(parser)
            .build()

    private fun addToCache(url: String, response: HttpResponse<*>) {
        cache.add(url, response)
    }

    private fun <T> loadFromCache(url: String): Single<T> =
        Single.fromCallable {
            cache.get(url).data as T
        }
}