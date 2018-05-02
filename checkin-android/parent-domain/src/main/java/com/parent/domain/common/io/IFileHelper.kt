package com.parent.domain.common.io

import android.app.Application
import android.net.Uri
import io.reactivex.Single
import java.io.File

/**
 * Created by mahmoud on 11/20/17.
 */
interface IFileHelper {
    fun getFile(uri: Uri): File

    fun getFile(application: Application, uri: Uri): Single<File>

    fun getFile(application: Application, uris: ArrayList<Uri>): Single<List<File>>

    fun readFileBytesFromUri(uri: Uri): ByteArray

    fun getFileSize(file: File): Long

    fun getFileExtension(file: File): String

    fun getFileThumbnail(file: File): File
}