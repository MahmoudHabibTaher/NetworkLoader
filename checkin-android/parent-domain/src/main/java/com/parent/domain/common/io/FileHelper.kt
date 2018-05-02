package com.parent.domain.common.io

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.net.Uri
import android.provider.MediaStore
import com.pavlospt.rxfile.RxFile
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream

/**
 * Created by mahmoud on 6/20/17.
 */
class FileHelper(val context: Context) : IFileHelper {
    override fun getFile(uri: Uri) = RxFile.createFileFromUri(context, uri).toBlocking().first()

    override fun getFile(application: Application, uri: Uri): Single<File> =
            Single.fromCallable {
                RxFile.createFileFromUri(application, uri).toBlocking().first()
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    override fun getFile(application: Application, uris: ArrayList<Uri>): Single<List<File>> =
            Single.fromCallable {
                RxFile.createFileFromUri(application, uris).toBlocking().first()
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    fun getFileObservable(uri: Uri) = RxFile.createFileFromUri(context, uri)

    override fun readFileBytesFromUri(uri: Uri): ByteArray {
        val file = File(uri.path)
        if (file.exists() && file.canRead()) {
            return file.readBytes()
        } else {
            throw FileNotFoundException("File at $uri not found or cannot read")
        }
    }

    fun readFileBytesObservable(uri: Uri, context: Context) =
            Observable.fromCallable { readFileBytesFromUri(uri) }

    override fun getFileSize(file: File): Long = file.length()

    fun getFileSizeObservable(file: File): Observable<Long> = Observable.fromCallable { getFileSize(file) }

    override fun getFileExtension(file: File): String =
            file.extension

    fun getFileExtensionsObservable(file: File): Observable<String> =
            Observable.fromCallable { getFileExtension(file) }

    override fun getFileThumbnail(file: File): File =
            saveFileThumbnail(ThumbnailUtils.createVideoThumbnail(file.path,
                    MediaStore.Images.Thumbnails.MINI_KIND), file.name)

    private fun saveFileThumbnail(bitmap: Bitmap, name: String): File =
            File.createTempFile("thumbnail_$name", "png", context.cacheDir).apply {
                val os = FileOutputStream(this)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, os)
                os.flush()
                os.close()
            }
}