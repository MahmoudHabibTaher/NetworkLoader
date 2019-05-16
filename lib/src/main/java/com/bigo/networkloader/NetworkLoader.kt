package com.bigo.networkloader

import com.bigo.networkloader.parser.ResponseParser
import io.reactivex.Single

interface NetworkLoader {
    fun <T> load(url: String, parser: ResponseParser<T>): Single<T>
}