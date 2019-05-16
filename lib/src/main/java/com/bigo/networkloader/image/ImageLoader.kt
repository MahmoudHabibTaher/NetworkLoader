package com.bigo.networkloader.image

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bigo.networkloader.parser.NetworkLoader
import com.bigo.networkloader.parser.ResponseParser
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference

class ImageLoader(
    private val networkLoader: NetworkLoader,
    private val parser: ResponseParser<Bitmap>
) {
    fun load(
        url: String,
        image: ImageView,
        placeholder: Drawable? = null,
        errorPlaceholder: Drawable? = null
    ) {
        val imageRef = WeakReference(image)
        imageRef.get()?.setImageDrawable(placeholder)
        loadBitmap(url, onSuccess = {
            imageRef.get()?.setImageBitmap(it)
        }, onError = {
            imageRef.get()?.setImageDrawable(errorPlaceholder)
        })
    }

    fun loadBitmap(url: String, onError: (Throwable) -> Unit, onSuccess: (Bitmap) -> Unit): Disposable =
        networkLoader.load(url, parser)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onError, onSuccess)
}