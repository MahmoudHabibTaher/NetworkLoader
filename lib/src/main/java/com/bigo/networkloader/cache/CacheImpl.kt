package com.bigo.networkloader.cache

import androidx.collection.LruCache
import com.bigo.networkloader.http.HttpResponse

class CacheImpl(capacity: Int) : Cache<String, HttpResponse<*>>(capacity) {

    private val lruCache = LruCache<String, HttpResponse<*>>(capacity)

    override fun add(key: String, value: HttpResponse<*>) {
        lruCache.put(key, value)
    }

    override fun get(key: String): HttpResponse<*>? =
        lruCache.get(key)

    override fun has(key: String): Boolean =
        lruCache.snapshot().containsKey(key)

    override fun clear() {
        lruCache.evictAll()
    }

    override fun size(): Int = lruCache.size()
}