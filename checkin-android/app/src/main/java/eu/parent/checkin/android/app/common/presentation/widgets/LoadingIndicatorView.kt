package eu.parent.android.app.common.presentation.widgets

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import eu.parent.android.app.R
import eu.parent.android.app.common.presentation.extensions.visible
import kotlin.properties.Delegates

/**
 * Created by mahmoud on 9/12/17.
 */
class LoadingIndicatorView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {
    companion object {
        private const val STATE_KEY_IS_LOADING = "is_loading"
        private const val STATE_KEY_IS_SUCCESS = "is_success"
        private const val STATE_KEY_IS_ERROR = "is_error"
        private const val STATE_KEY_MESSAGE = "message"

        private const val DEFAULT_FADE_ANIM = 250L
        private const val DEFAULT_LAYOUT = R.layout.default_loading_layout
        private const val DEFAULT_COLOR_RES_ID = R.color.blueRegular
        private const val DEFAULT_LOADING_DRAWABLE = R.drawable.ic_parent_loader
    }

    var loadingDrawable: Drawable? = null
        set(value) {
            field = value
            initView()
        }
    var successDrawable: Drawable? = null
        set(value) {
            field = value
            initView()
        }
    var errorDrawable: Drawable? = null
        set(value) {
            field = value
            initView()
        }

    var loadingTintColor: Int = 0
        set(value) {
            field = value
            initView()
        }
    var successTintColor: Int = 0
        set(value) {
            field = value
            initView()
        }
    var errorTintColor: Int = 0
        set(value) {
            field = value
            initView()
        }
    var messageTextColor: Int = 0
        set(value) {
            field = value
            initView()
        }

    var isSaveViewState = true

    private var loadingView: ProgressBar? = null
    private var successView: ImageView? = null
    private var errorView: ImageView? = null
    private var messageTextView: TextView? = null

    private var isLoadingVisible: Boolean by Delegates.observable(false) { _, _, newValue ->
        updateLoadingIndicator(newValue)
    }

    private var hideLoadingOnFinish = true

    private var isSuccessVisible: Boolean by Delegates.observable(false) { _, _, newValue ->
        updateSuccessIndicator(newValue)
    }
    private var isErrorVisible: Boolean by Delegates.observable(false) { _, _, newValue ->
        updateErrorIndicator(newValue)
    }

    private var isMessageVisible: Boolean by Delegates.observable(false) { _, _, newValue ->
        updateMessageView(message, newValue)
    }

    private var message: String by Delegates.observable("") { _, _, newValue ->
        updateMessageView(newValue, isMessageVisible)
    }

    init {
        isSaveEnabled = true

        val inflater = LayoutInflater.from(context)
        inflater.inflate(DEFAULT_LAYOUT, this)

        attrs?.let {
            val a = context.obtainStyledAttributes(it, R.styleable.LoadingIndicatorView, defStyleAttr, 0)

            val defaultColor = getColor(DEFAULT_COLOR_RES_ID)

            loadingDrawable = a.getDrawable(R.styleable.LoadingIndicatorView_loadingDrawable) ?: ContextCompat.getDrawable(context, DEFAULT_LOADING_DRAWABLE)

            loadingTintColor = a.getColor(R.styleable.LoadingIndicatorView_loadingIndicatorTintColor, defaultColor)

            successDrawable = a.getDrawable(R.styleable.LoadingIndicatorView_successDrawable)
            successTintColor = a.getColor(R.styleable.LoadingIndicatorView_successIndicatorTintColor, defaultColor)

            errorDrawable = a.getDrawable(R.styleable.LoadingIndicatorView_errorDrawable)
            errorTintColor = a.getColor(R.styleable.LoadingIndicatorView_errorIndicatorTintColor, defaultColor)

            messageTextColor = a.getColor(R.styleable.LoadingIndicatorView_messageTextColor, defaultColor)

            a.recycle()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        loadingView = findViewById(R.id.loading_image_view)
        successView = findViewById(R.id.loading_success_view)
        errorView = findViewById(R.id.loading_error_view)
        messageTextView = findViewById(R.id.message_text_view)

        initView()

        isLoadingVisible = false
        isSuccessVisible = false
        isErrorVisible = false
    }

    override fun onSaveInstanceState(): Parcelable =
            if (isSaveViewState) SavedState(isLoadingVisible,
                    isSuccessVisible,
                    isErrorVisible,
                    message,
                    isMessageVisible, super.onSaveInstanceState())
            else super.onSaveInstanceState()

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (isSaveViewState && state != null && state is SavedState) {
            super.onRestoreInstanceState(state.superState)

            isLoadingVisible = state.loading
            isSuccessVisible = state.success
            isErrorVisible = state.error
            message = state.message
            isMessageVisible = state.messageVisible
        } else {
            super.onRestoreInstanceState(state)
        }
    }

    fun setLoading(loading: Boolean) {
        isLoadingVisible = loading
        isSuccessVisible = false
        isErrorVisible = false
        isMessageVisible = false
    }

    fun setHideLoadingOnFinish(hide: Boolean) {
        hideLoadingOnFinish = hide
    }

    fun setSuccess(message: String) {
        this.message = message
        isSuccessVisible = true
        isMessageVisible = true
        isErrorVisible = false
        isLoadingVisible = false
    }

    fun setError(message: String) {
        this.message = message
        isErrorVisible = true
        isMessageVisible = true
        isSuccessVisible = false
        isLoadingVisible = false
    }

    private fun initView() {
        tintDrawable(loadingDrawable, loadingTintColor)
        loadingView?.isIndeterminate = true
        loadingView?.indeterminateDrawable = loadingDrawable

        tintDrawable(successDrawable, successTintColor)
        successView?.setImageDrawable(successDrawable)

        tintDrawable(errorDrawable, errorTintColor)
        errorView?.setImageDrawable(errorDrawable)

        messageTextView?.setTextColor(messageTextColor)
    }

    private fun getColor(@ColorRes colorRes: Int) =
            ContextCompat.getColor(context, colorRes)

    private fun tintDrawable(drawable: Drawable?, color: Int) {
        drawable?.mutate()?.setColorFilter(color, PorterDuff.Mode.SRC_IN)
    }

    private fun updateLoadingIndicator(visible: Boolean) {
        if (!visible) {
            if (hideLoadingOnFinish) {
                fadeView(false, loadingView)
            } else {
                loadingView?.isIndeterminate = false
            }
        } else {
            loadingView?.isIndeterminate = true
            fadeView(true, loadingView)
        }
    }

    private fun updateSuccessIndicator(visible: Boolean) {
        fadeView(visible, successView)
    }

    private fun updateErrorIndicator(visible: Boolean) {
        fadeView(visible, errorView)
    }

    private fun updateMessageView(message: String, visible: Boolean) {
        messageTextView?.text = message
        fadeView(visible, messageTextView)
    }

    private fun fadeView(visible: Boolean, view: View?, doOnAnimationEnd: () -> Unit = {}) {
        val alpha = if (visible) 1.0f else 0.0f
        view?.animate()
                ?.alpha(alpha)
                ?.setDuration(DEFAULT_FADE_ANIM)
                ?.setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationStart(animation: Animator?) {
                        super.onAnimationStart(animation)
                        if (visible) {
                            view.visible(true)
                        }
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        if (!visible) {
                            view.visible(false)
                        }

                        doOnAnimationEnd()
                    }
                })
                ?.start()
    }

    class SavedState(var loading: Boolean,
                     var success: Boolean,
                     var error: Boolean,
                     var message: String,
                     var messageVisible: Boolean,
                     superState: Parcelable?) : BaseSavedState(superState), Parcelable {
        constructor(source: Parcel) : this(
                1 == source.readInt(),
                1 == source.readInt(),
                1 == source.readInt(),
                source.readString(),
                1 == source.readInt(),
                source.readParcelable(BaseSavedState::class.java.classLoader)
                        ?: BaseSavedState.EMPTY_STATE
        )

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
            writeInt((if (loading) 1 else 0))
            writeInt((if (success) 1 else 0))
            writeInt((if (error) 1 else 0))
            writeString(message)
            writeInt((if (messageVisible) 1 else 0))
            writeParcelable(superState, 0)
        }

        companion object {
            @JvmField
            val CREATOR: Parcelable.Creator<SavedState> = object : Parcelable.Creator<SavedState> {
                override fun createFromParcel(source: Parcel): SavedState = SavedState(source)
                override fun newArray(size: Int): Array<SavedState?> = arrayOfNulls(size)
            }
        }
    }
}