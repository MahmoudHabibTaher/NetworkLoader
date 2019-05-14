package com.bigo.networkloader.cache

import com.bigo.networkloader.http.HttpResponse
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CacheImplTest {

    private lateinit var cache: CacheImpl

    @Test
    fun testSetCapacity() {
        val capacity = 5

        cache = CacheImpl(capacity)

        assertEquals(cache.capacity, 5)
    }

    @Test
    fun testAddWhenCacheIsEmpty() {
        cache = CacheImpl(5)

        val url = "https://www.example.com"
        val data = "Data"
        val value = HttpResponse(url, 200, data)

        cache.add(url, value)

        assertEquals(1, cache.size())

        val url2 = "https://www.example2.com"
        val data2 = "Another data"
        val value2 = HttpResponse(url2, 200, data2)

        cache.add(url2, value2)

        assertEquals(2, cache.size())
    }

    @Test
    fun testAddWhenCacheIsFull() {
        cache = CacheImpl(2)

        val url = "https://www.example.com"
        val data = "Data"
        val value = HttpResponse(url, 200, data)

        cache.add(url, value)

        val url2 = "https://www.example2.com"
        val data2 = "Another data"
        val value2 = HttpResponse(url2, 200, data2)

        cache.add(url2, value2)

        val url3 = "https://www.example3.com"
        val data3 = "Yet Another data"
        val value3 = HttpResponse(url3, 200, data3)

        cache.add(url3, value3)

        assertFalse(cache.has(url))

        assertTrue(cache.has(url3))

        assertEquals(2, cache.size())
    }

    @Test
    fun testGetWhenExist() {
        cache = CacheImpl(2)

        val url = "https://www.example.com"
        val data = "Data"
        val value = HttpResponse(url, 200, data)

        cache.add(url, value)

        val result = cache.get(url)

        assertEquals(value, result)

        val url2 = "https://www.example2.com"
        val data2 = "Another data"
        val value2 = HttpResponse(url2, 200, data2)

        cache.add(url2, value2)

        val result2 = cache.get(url2)

        assertEquals(value2, result2)
    }

    @Test
    fun testClear() {
        cache = CacheImpl(2)

        val url = "https://www.example.com"
        val data = "Data"
        val value = HttpResponse(url, 200, data)

        cache.add(url, value)

        cache.clear()

        assertEquals(0, cache.size())
    }
}