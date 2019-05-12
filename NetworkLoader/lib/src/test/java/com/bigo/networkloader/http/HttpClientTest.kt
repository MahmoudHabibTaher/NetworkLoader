package com.bigo.networkloader.http

import android.net.ParseException
import com.bigo.networkloader.http.builder.ConnectionBuilder
import com.bigo.networkloader.http.exceptions.HttpException
import com.bigo.networkloader.parser.ResponseParser
import com.nhaarman.mockitokotlin2.*
import org.junit.Before

import org.junit.Test
import java.io.InputStream
import javax.net.ssl.HttpsURLConnection

class HttpClientTest {

    private val connectionBuilder = mock<ConnectionBuilder>()

    private lateinit var httpClient: HttpClient

    @Before
    fun setUp() {
        httpClient = HttpClient(connectionBuilder)
    }

    @Test
    fun testGetSuccess() {
        val url = "https://www.google.com"
        val result = "Hello, World!"

        val connection = mock<HttpsURLConnection>()

        val inputStream = mock<InputStream>()

        val parser = mock<ResponseParser<String>>()

        val request = HttpRequest.Builder<String>()
            .url(url)
            .parser(parser)
            .build()

        whenever(connectionBuilder.build(any(), any(), any())) doReturn connection

        whenever(connection.inputStream) doReturn inputStream

        whenever(connection.responseCode) doReturn HttpsURLConnection.HTTP_OK

        whenever(parser.parse(any())) doReturn result

        val testObserver = httpClient.get(request).test()

        testObserver.awaitTerminalEvent()

        inOrder(connection, inputStream, parser) {
            verify(connection).connect()

            verify(parser).parse(inputStream)

            verify(inputStream).close()

            verify(connection).disconnect()
        }

        val httpResponse = HttpResponse(url, connection.responseCode, result)

        testObserver.assertValue(httpResponse)
    }

    @Test
    fun testGetSuccessParsingError() {
        val url = "https://www.google.com"
        val result = "Hello, World!"

        val connection = mock<HttpsURLConnection>()

        val inputStream = mock<InputStream>()

        val parser = mock<ResponseParser<String>>()

        val request = HttpRequest.Builder<String>()
            .url(url)
            .parser(parser)
            .build()

        whenever(connectionBuilder.build(any(), any(), any())) doReturn connection

        whenever(connection.inputStream) doReturn inputStream

        whenever(connection.responseCode) doReturn HttpsURLConnection.HTTP_OK

        whenever(parser.parse(any())) doThrow ParseException::class

        val testObserver = httpClient.get(request).test()

        testObserver.awaitTerminalEvent()

        inOrder(connection, inputStream, parser) {
            verify(connection).connect()

            verify(parser).parse(inputStream)

            verify(inputStream).close()

            verify(connection).disconnect()
        }

        testObserver.assertError(ParseException::class.java)
    }

    @Test
    fun testGetError() {
        val url = "https://www.example.com"
        val responseCode = HttpsURLConnection.HTTP_BAD_REQUEST
        val responseMessage = "Response message error"

        val connection = mock<HttpsURLConnection>()

        val parser = mock<ResponseParser<String>>()

        val request = HttpRequest.Builder<String>()
            .url(url)
            .parser(parser)
            .build()

        whenever(connectionBuilder.build(any(), any(), any())) doReturn connection

        whenever(connection.responseCode) doReturn responseCode

        whenever(connection.responseMessage) doReturn responseMessage

        val testObserver = httpClient.get(request).test()

        testObserver.awaitTerminalEvent()

        inOrder(connection) {
            verify(connection).connect()

            verify(connection).disconnect()
        }

        testObserver.assertError {
            it is HttpException
                    && it.errorBody == responseMessage
                    && it.errorCode == responseCode
        }
    }
}