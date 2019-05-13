package com.bigo.networkloader.parser

import android.net.ParseException
import java.io.InputStream

interface ResponseParser<T> {
    @Throws(ParseException::class)
    fun parse(inputStream: InputStream): T
}