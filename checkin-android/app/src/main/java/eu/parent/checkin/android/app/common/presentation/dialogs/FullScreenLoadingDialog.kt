package eu.parent.android.app.common.presentation.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.parent.android.app.R
import eu.parent.android.app.common.presentation.extensions.setArgs
import eu.parent.android.app.common.presentation.widgets.LoadingIndicatorView
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates

/**
 * Created by mahmoud on 9/29/17.
 */
class FullScreenLoadingDialog : KodeinDialogFragment() {
    companion object {
        private const val STATE_IS_LOADING = "is_loading"
        private const val STATE_IS_SUCCESS = "is_success"
        private const val STATE_IS_ERROR = "is_error"
        private const val STATE_MESSAGE = "message"

        private const val ARG_BACKGROUND_DRAWABLE_RES = "background_drawable_res"
        private const val ARG_LOADING_DRAWABLE_RES = "loading_indicator_drawable_res"
        private const val ARG_SUCCESS_DRAWABLE_RES = "success_drawable_res"
        private const val ARG_ERROR_DRAWABLE_RES = "error_drawable_res"
        private const val ARG_LOADING_DRAWABLE_TINT = "loading_drawable_tint"
        private const val ARG_SUCCESS_DRAWABLE_TINT = "success_drawable_tint"
        private const val ARG_ERROR_DRAWABLE_TINT = "error_drawable_tint"
        private const val ARG_MESSAGE_TINT = "message_tint"

        private const val DEFAULT_COLOR_TINT = R.color.grayIconLight

        fun newInstance(@DrawableRes backgroundDrawableRes: Int = R.drawable.full_screen_loading_dialog_background,
                        @DrawableRes loadingDrawableRes: Int = R.drawable.loading_drawable,
                        @DrawableRes successDrawableRes: Int = R.drawable.loading_success_drawable,
                        @DrawableRes errorDrawableRes: Int = R.drawable.loading_error_drawable,
                        @ColorRes loadingDrawableTint: Int = DEFAULT_COLOR_TINT,
                        @ColorRes successDrawableTint: Int = DEFAULT_COLOR_TINT,
                        @ColorRes errorDrawableTint: Int = DEFAULT_COLOR_TINT,
                        @ColorRes messageTintColor: Int = DEFAULT_COLOR_TINT) =
                FullScreenLoadingDialog().apply {
                    setArgs(ARG_BACKGROUND_DRAWABLE_RES to backgroundDrawableRes,
                            ARG_LOADING_DRAWABLE_RES to loadingDrawableRes,
                            ARG_SUCCESS_DRAWABLE_RES to successDrawableRes,
                            ARG_ERROR_DRAWABLE_RES to errorDrawableRes,
                            ARG_LOADING_DRAWABLE_TINT to loadingDrawableTint,
                            ARG_SUCCESS_DRAWABLE_TINT to successDrawableTint,
                            ARG_ERROR_DRAWABLE_TINT to errorDrawableTint,
                            ARG_MESSAGE_TINT to messageTintColor)
                }
    }

    private var loadingIndicatorView: LoadingIndicatorView? = null
    private var backgroundView: View? = null

    @DrawableRes private var backgroundDrawableRes: Int = R.drawable.full_screen_loading_dialog_background
    @DrawableRes private var loadingDrawableRes: Int = R.drawable.loading_drawable
    @DrawableRes private var successDrawableRes: Int = R.drawable.loading_success_drawable
    @DrawableRes private var errorDrawableRes: Int = R.drawable.loading_error_drawable
    @ColorRes private var loadingDrawableTint: Int = DEFAULT_COLOR_TINT
    @ColorRes private var successDrawableTint: Int = DEFAULT_COLOR_TINT
    @ColorRes private var errorDrawableTint: Int = DEFAULT_COLOR_TINT
    @ColorRes private var messageTintColor: Int = DEFAULT_COLOR_TINT

    private var disposable: Disposable? = null

    private var message = ""

    private var isLoadingVisible by Delegates.observable(true) { _, _, newValue ->
        loadingIndicatorView?.setLoading(newValue)
    }

    private var isSuccessVisible by Delegates.observable(false) { _, _, newValue ->
        if (newValue) {
            loadingIndicatorView?.setSuccess(message)
        }
    }

    private var isErrorVisible by Delegates.observable(false) { _, _, newValue ->
        if (newValue) {
            loadingIndicatorView?.setError(message)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setStyle(STYLE_NO_FRAME, R.style.AppTheme)
        super.onCreate(savedInstanceState)

        backgroundDrawableRes = arguments?.getInt(ARG_BACKGROUND_DRAWABLE_RES) ?: R.drawable.full_screen_loading_dialog_background
        loadingDrawableRes = arguments?.getInt(ARG_LOADING_DRAWABLE_RES) ?: R.drawable.loading_drawable
        successDrawableRes = arguments?.getInt(ARG_SUCCESS_DRAWABLE_RES) ?: R.drawable.loading_success_drawable
        errorDrawableRes = arguments?.getInt(ARG_ERROR_DRAWABLE_RES) ?: R.drawable.loading_error_drawable
        loadingDrawableTint = arguments?.getInt(ARG_LOADING_DRAWABLE_TINT) ?: DEFAULT_COLOR_TINT
        successDrawableTint = arguments?.getInt(ARG_SUCCESS_DRAWABLE_TINT) ?: DEFAULT_COLOR_TINT
        errorDrawableRes = arguments?.getInt(ARG_ERROR_DRAWABLE_TINT) ?: DEFAULT_COLOR_TINT
        messageTintColor = arguments?.getInt(ARG_MESSAGE_TINT) ?: DEFAULT_COLOR_TINT

        isCancelable = false
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.dialog_fragment_full_screen_loading, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view?.let {
            initView(it, savedInstanceState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        val bundle = Bundle(outState)
        bundle.putBoolean(STATE_IS_LOADING, isLoadingVisible)
        bundle.putBoolean(STATE_IS_SUCCESS, isSuccessVisible)
        bundle.putBoolean(STATE_IS_ERROR, isErrorVisible)
        bundle.putString(STATE_MESSAGE, message)
        super.onSaveInstanceState(bundle)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

    fun setLoading(loading: Boolean) {
        isLoadingVisible = loading
    }

    fun setSuccess(message: String) {
        this.message = message
        isSuccessVisible = true
    }

    fun setError(message: String) {
        this.message = message
        isErrorVisible = true
    }

    fun hideAfter(timeMillis: Long, onHide: () -> Unit = {}) {
        disposable = Completable.timer(timeMillis, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    onHide()
                    if (isAdded) {
                        dismiss()
                    }
                }
    }

    private fun initView(view: View, savedInstanceState: Bundle?) {
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        backgroundView = view.findViewById(R.id.loading_background_view)
        loadingIndicatorView = view.findViewById(R.id.loading_indicator_view)
        backgroundView?.background = getDrawable(backgroundDrawableRes)
        loadingIndicatorView?.apply {
            loadingDrawable = getDrawable(loadingDrawableRes)
            loadingTintColor = getColor(loadingDrawableTint)

            successDrawable = getDrawable(successDrawableRes)
            successTintColor = getColor(successDrawableTint)

            errorDrawable = getDrawable(errorDrawableRes)
            errorTintColor = getColor(errorDrawableTint)

            messageTextColor = getColor(messageTintColor)
            isSaveViewState = false
        }

        message = savedInstanceState?.getString(STATE_MESSAGE) ?: ""
        isLoadingVisible = savedInstanceState?.getBoolean(STATE_IS_LOADING) ?: true
        isSuccessVisible = savedInstanceState?.getBoolean(STATE_IS_SUCCESS) ?: false
        isErrorVisible = savedInstanceState?.getBoolean(STATE_IS_ERROR) ?: false
    }

    private fun getDrawable(@DrawableRes drawableRes: Int) = ContextCompat.getDrawable(context, drawableRes)

    private fun getColor(@ColorRes colorRes: Int) = ContextCompat.getColor(context, colorRes)
}