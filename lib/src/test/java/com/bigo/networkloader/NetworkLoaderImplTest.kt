package com.bigo.networkloader

import com.bigo.networkloader.cache.Cache
import com.bigo.networkloader.http.HttpClient
import com.bigo.networkloader.http.HttpRequest
import com.bigo.networkloader.http.HttpResponse
import com.bigo.networkloader.http.exceptions.HttpException
import com.bigo.networkloader.parser.ResponseParser
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import javax.net.ssl.HttpsURLConnection

class NetworkLoaderImplTest {

    private val client = mock<HttpClient>()

    private val cache = mock<Cache<String, HttpResponse<*>>>()

    private lateinit var networkLoader: NetworkLoaderImpl

    @Before
    fun setUp() {
        networkLoader = NetworkLoaderImpl(client, cache)
    }

    @Test
    fun testLoadSuccess() {
        val url = "https://wwww.example.com"
        val parser = mock<ResponseParser<String>>()
        val result = "Hello, World!"

        val request = HttpRequest.Builder<String>()
            .url(url)
            .parser(parser)
            .build()

        val response = HttpResponse(url, HttpsURLConnection.HTTP_OK, result)

        whenever(cache.has(url)) doReturn false

        whenever(client.get(request)) doReturn Single.just(response)

        val testObserver = networkLoader.load(url, parser).test()

        testObserver.awaitTerminalEvent()

        verify(client).get(request)

        testObserver.assertValue(result)
    }

    @Test
    fun testLoadError() {
        val url = "https://wwww.example.com"
        val parser = mock<ResponseParser<String>>()
        val resultError = "Hello, World!"

        val request = HttpRequest.Builder<String>()
            .url(url)
            .parser(parser)
            .build()

        whenever(cache.has(url)) doReturn false

        whenever(client.get(request)) doReturn Single.error(HttpException(404, resultError))

        val testObserver = networkLoader.load(url, parser).test()

        testObserver.awaitTerminalEvent()

        verify(client).get(request)

        testObserver.assertError(HttpException::class.java)
        testObserver.assertErrorMessage(resultError)
    }

    @Test
    fun testLoadSuccessAddToCache() {
        val url = "https://wwww.example.com"
        val parser = mock<ResponseParser<String>>()
        val result = "Hello, World!"

        val request = HttpRequest.Builder<String>()
            .url(url)
            .parser(parser)
            .build()

        val response = HttpResponse(url, HttpsURLConnection.HTTP_OK, result)

        whenever(cache.has(url)) doReturn false

        whenever(client.get(request)) doReturn Single.just(response)

        val testObserver = networkLoader.load(url, parser).test()

        testObserver.awaitTerminalEvent()

        verify(client).get(request)

        verify(cache).add(url, response)

        testObserver.assertValue(result)
    }

    @Test
    fun testDataInCacheLoadFromCache() {
        val url = "https://wwww.example.com"
        val parser = mock<ResponseParser<String>>()
        val result = "Hello, World!"

        val response = HttpResponse(url, HttpsURLConnection.HTTP_OK, result)

        val request = HttpRequest.Builder<String>()
            .url(url)
            .parser(parser)
            .build()

        whenever(client.get(request)) doReturn Single.just(response)

        whenever(cache.has(url)) doReturn true

        whenever(cache.get(url)) doReturn response

        val testObserver = networkLoader.load(url, parser).test()

        testObserver.awaitTerminalEvent()

        verify(cache).has(url)

        verify(cache).get(url)

        verifyZeroInteractions(client)

        testObserver.assertValue(result)
    }
}