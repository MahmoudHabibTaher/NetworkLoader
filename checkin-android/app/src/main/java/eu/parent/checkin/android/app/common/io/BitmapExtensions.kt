package eu.parent.android.app.common.io

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.support.v4.graphics.drawable.DrawableCompat
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.v7.content.res.AppCompatResources
import eu.parent.android.app.R
import android.util.TypedValue
import android.app.Activity
import android.app.Application
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.support.media.ExifInterface
import android.util.Log
import com.bumptech.glide.load.resource.bitmap.TransformationUtils
import com.parent.domain.DomainIntegration.getApplication
import com.parent.domain.common.date.now
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


/**
 * Created by Raed Ezzat on 15/12/2017.
 */
fun Int.getBitmapFromResources(context: Context): Bitmap {
    var drawable = AppCompatResources.getDrawable(context, this)
            ?: AppCompatResources.getDrawable(context, R.drawable.ic_logo)
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
        drawable = DrawableCompat.wrap(drawable!!).mutate()
    }

    val bitmap = Bitmap.createBitmap(drawable?.intrinsicWidth!!,
            drawable?.intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight())
    drawable.draw(canvas)

    return bitmap
}

fun Drawable.getBitmapFromDrawable(context: Context): Bitmap {
    var drawable = this
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
        drawable = DrawableCompat.wrap(drawable!!).mutate()
    }

    val bitmap = Bitmap.createBitmap(drawable?.intrinsicWidth!!,
            drawable?.intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight())
    drawable.draw(canvas)

    return bitmap
}

fun getPixelsFromDPs(context: Context, dps: Int): Int {
    val r = context.resources
    return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, r.getDimension(dps), r.displayMetrics).toInt()
}
