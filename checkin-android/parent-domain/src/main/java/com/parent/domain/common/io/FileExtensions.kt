package com.parent.domain.common.io

import android.content.Context
import android.net.Uri
import android.provider.MediaStore


/**
 * Created by mahmoud on 11/20/17.
 */
fun Uri.getRealPath(context: Context): String {
    var filePath = ""
    val cursor = context.contentResolver.query(this, null, null, null, null)
    if (cursor == null) {
        filePath = this.path
    } else {
        if (cursor.moveToNext()) {
            val columnIndex = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            filePath = cursor.getString(columnIndex)
        }
        cursor.close()
    }
    return filePath
}