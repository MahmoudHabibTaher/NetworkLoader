package com.bigo.networkloader.http

import io.reactivex.Single

interface HttpClient {
    fun <T> get(request: HttpRequest<T>): Single<HttpResponse<T>>
}