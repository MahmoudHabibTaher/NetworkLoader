package com.bigo.networkloader.cache

abstract class Cache<Key, Value>(private val capacity: Int) {
    abstract fun add(key: Key, value: Value)

    abstract fun remove(key: Key): Value

    abstract fun get(key: Key): Value

    abstract fun clear()

    abstract fun size(): Int

    abstract fun capacity(): Int
}