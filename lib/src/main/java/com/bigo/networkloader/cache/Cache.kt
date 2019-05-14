package com.bigo.networkloader.cache

abstract class Cache<Key, Value>(val capacity: Int) {
    abstract fun add(key: Key, value: Value)

    abstract fun get(key: Key): Value?

    abstract fun has(key: Key): Boolean

    abstract fun clear()

    abstract fun size(): Int
}