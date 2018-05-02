package com.parent.domain.base

/**
 * Created by mahmoud on 6/1/17.
 */
open class Params(val map: Map<String, *>) {
    constructor(vararg pairs: Pair<String, *>) : this(pairs.toMap())

    inline fun <reified T> get(key: String, defaultValue: T? = null): T {
        return if (map.containsKey(key)) {
            map[key].takeIf { it is T } as T
        } else {
            defaultValue!!
        }
    }

    override fun toString(): String = map.toString()

    override fun equals(other: Any?): Boolean = when (other) {
        null -> false
        is Params -> map == other.map
        else -> false
    }

    override fun hashCode(): Int = map.hashCode()
}

fun emptyParams(): Params = Params(mapOf<String, Any>())