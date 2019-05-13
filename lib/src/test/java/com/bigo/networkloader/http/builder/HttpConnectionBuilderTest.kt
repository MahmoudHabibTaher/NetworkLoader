package com.bigo.networkloader.http.builder

import com.bigo.networkloader.http.HttpMethods
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class HttpConnectionBuilderTest {
    private lateinit var connectionBuilder: HttpConnectionBuilder

    @Before
    fun setUp() {
        connectionBuilder = HttpConnectionBuilder()
    }

    @Test
    fun testBuild() {
        val url = "https://www.example.com"
        val readTimeout = 3000
        val connectTimeout = 2000

        val connection = connectionBuilder.build(url, readTimeout, connectTimeout)

        assertNotNull(connection)

        connection?.apply {
            assertEquals(this.url.toString(), url)
            assertEquals(this.readTimeout, readTimeout)
            assertEquals(this.connectTimeout, connectTimeout)
            assertEquals(this.requestMethod, HttpMethods.GET)
            assertTrue(this.doInput)
        }
    }
}