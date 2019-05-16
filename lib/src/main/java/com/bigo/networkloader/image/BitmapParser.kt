package com.bigo.networkloader.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.bigo.networkloader.parser.ResponseParser
import java.io.InputStream

class BitmapParser : ResponseParser<Bitmap> {
    override fun parse(inputStream: InputStream): Bitmap =
        BitmapFactory.decodeStream(inputStream)
}