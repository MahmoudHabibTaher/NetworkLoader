package com.bigo.networkloader.demo.core.network.parsers

import com.bigo.networkloader.parser.ResponseParser
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import java.io.InputStream
import java.io.InputStreamReader

class JsonParser<T>(
    private val gson: Gson,
    private val type: TypeToken<T>
) : ResponseParser<T> {
    companion object {
        inline fun <reified T> create(gson: Gson) =
            JsonParser(gson, object : TypeToken<T>() {})
    }

    override fun parse(inputStream: InputStream): T =
        gson.fromJson(createReader(inputStream), type.type)

    private fun createReader(inputStream: InputStream) =
        JsonReader(InputStreamReader(inputStream, "UTF-8"))
}