package com.bigo.networkloader.http

data class HttpResponse<T>(
    val url: String,
    val code: Int,
    val data: T?
)