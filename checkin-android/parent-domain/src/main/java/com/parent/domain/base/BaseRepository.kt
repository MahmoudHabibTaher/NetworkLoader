package com.parent.domain.base

/**
 * Created by mahmoud on 11/28/17.
 */
abstract class BaseRepository {
    open protected var cacheInvalid: Boolean = true

    open fun setCacheValid(valid: Boolean) {
        cacheInvalid = !valid
    }

    open fun isCacheInvalid() = cacheInvalid
}