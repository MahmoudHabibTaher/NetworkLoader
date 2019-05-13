package com.bigo.networkloader.http

import com.bigo.networkloader.parser.ResponseParser

data class HttpRequest<T> private constructor(
    val url: String,
    val parser: ResponseParser<T>?
) {
    class Builder<T> {
        private var url = ""
        private var parser: ResponseParser<T>? = null

        fun url(url: String) = apply {
            this.url = url
        }

        fun parser(parser: ResponseParser<T>) = apply {
            this.parser = parser
        }

        fun build() = HttpRequest(url, parser)
    }
}