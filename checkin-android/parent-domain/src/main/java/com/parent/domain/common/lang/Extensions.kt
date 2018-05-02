package com.parent.domain.common.lang

import android.webkit.URLUtil

/**
 * Created by mahmoud on 11/25/17.
 */
fun Int.toBoolean() = this > 0

fun Boolean.toInt() = if (this) 1 else 0

fun <T> List<T>.toCommaSpearated() = foldIndexed("") { index, acc, item ->
    acc + if (index < size - 1) {
        "$item, "
    } else {
        item.toString()
    }
}

fun String.isUrl():Boolean =
        URLUtil.isValidUrl(this)


fun String.mapRemoteStringToBoolean(): Boolean =
        when (this) {
            "1" -> true
            else -> false
        }

fun Boolean.mapRemoteBooleanToString(): String =
        when (this) {
            false -> "0"
            true -> "1"
        }