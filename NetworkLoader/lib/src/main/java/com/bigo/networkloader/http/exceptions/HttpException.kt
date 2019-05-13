package com.bigo.networkloader.http.exceptions

import java.lang.Exception

class HttpException(
    val errorCode: Int,
    val errorBody: String
) : Exception(errorBody)