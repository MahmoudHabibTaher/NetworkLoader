package eu.parent.android.app.common.presentation.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.support.annotation.DrawableRes
import android.transition.Transition
import android.widget.ImageView
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.manager.TargetTracker
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import eu.parent.android.app.common.presentation.glide.GlideApp
import java.io.File
import com.bumptech.glide.request.target.SizeReadyCallback
import android.graphics.drawable.BitmapDrawable
import com.bumptech.glide.request.target.BaseTarget
import eu.parent.android.app.R


/**
 * Created by mahmoud on 6/19/17.
 */
fun ImageView.loadCircleCroppedImage(imageUrl: String,
                                     @DrawableRes placeHolder: Int = -1,
                                     @DrawableRes errorPlaceHolder: Int = -1) {
    loadImage(imageUrl, placeHolder, errorPlaceHolder, true)
}

fun ImageView.loadCircleCroppedImage(imageUri: Uri, @DrawableRes placeHolder: Int = -1,
                                     @DrawableRes errorPlaceHolder: Int = -1) {
    loadImage(imageUri, placeHolder, errorPlaceHolder, true)
}

@SuppressLint("CheckResult")
fun ImageView.loadImage(imageUrl: String,
                        @DrawableRes placeHolder: Int = -1,
                        @DrawableRes errorPlaceHolder: Int = -1,
                        circleCrop: Boolean = false,
                        transformation: Transformation<Bitmap>? = null) {
    GlideApp.with(this).load(imageUrl)
            .apply(RequestOptions().apply {
                if (placeHolder > -1) {
                    placeholder(placeHolder)
                }
                if (errorPlaceHolder > -1) {
                    error(errorPlaceHolder)
                }
                if (circleCrop) {
                    circleCrop()
                }
                if (transformation != null) {
                    transform(transformation)
                }
            }).into(this)
}

@SuppressLint("CheckResult")
fun loadImageIntoDrawable(imageUrl: String,
                          context: Context,
                          onSuccess: (Drawable) -> Unit = {},
                          circleCrop: Boolean = false,
                          transformation: Transformation<Bitmap>? = null) {

    var singleTarget: SimpleTarget<Drawable> = object : SimpleTarget<Drawable>() {
        override fun onResourceReady(resource: Drawable?, transition: com.bumptech.glide.request.transition.Transition<in Drawable>?) {
            onSuccess(resource ?: context.resources.getDrawable(R.drawable.ic_user_profile))
        }
    }
    GlideApp.with(context).load(imageUrl)
            .apply(RequestOptions().apply {
                if (circleCrop) {
                    circleCrop()
                }
                if (transformation != null) {
                    transform(transformation)
                }
            }).into(singleTarget)
}

@SuppressLint("CheckResult")
fun ImageView.loadImage(imageUri: Uri,
                        @DrawableRes placeHolder: Int = -1,
                        @DrawableRes errorPlaceHolder: Int = -1,
                        circleCrop: Boolean = false,
                        transformation: Transformation<Bitmap>? = null) {
    GlideApp.with(this).load(imageUri)
            .apply(RequestOptions().apply {
                if (placeHolder > -1) {
                    placeholder(placeHolder)
                }
                if (errorPlaceHolder > -1) {
                    error(errorPlaceHolder)
                }
                if (circleCrop) {
                    circleCrop()
                }
                if (transformation != null) {
                    transform(transformation)
                }
            }).into(this)
}

@SuppressLint("CheckResult")
fun ImageView.loadImage(file: File,
                        @DrawableRes placeHolder: Int = -1,
                        @DrawableRes errorPlaceHolder: Int = -1,
                        circleCrop: Boolean = false,
                        transformation: Transformation<Bitmap>? = null) {
    GlideApp.with(this).load(file)
            .apply(RequestOptions().apply {
                if (placeHolder > -1) {
                    placeholder(placeHolder)
                }
                if (errorPlaceHolder > -1) {
                    error(errorPlaceHolder)
                }
                if (circleCrop) {
                    circleCrop()
                }
                if (transformation != null) {
                    transform(transformation)
                }
            }).into(this)
}

