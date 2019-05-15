package com.bigo.networkloader.parser

import io.reactivex.Single

interface NetworkLoader {
    fun <T> load(url: String, parser: ResponseParser<T>): Single<T>
}