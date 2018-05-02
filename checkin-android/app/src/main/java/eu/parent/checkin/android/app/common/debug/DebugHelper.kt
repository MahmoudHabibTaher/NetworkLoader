package eu.parent.android.app.common.debug

import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric

/**
 * Created by mahmoud on 12/26/17.
 */
object DebugHelper {
    private const val KEY_SCREEN = "screen_name"
    private const val KEY_USER_NAME = "user_name"

    fun setCurrentScreen(screenName: String) {
        if (Fabric.isInitialized()) {
            Crashlytics.setString(KEY_SCREEN, screenName)
        }
    }

    fun setCurrentUserName(userName: String) {
        if (Fabric.isInitialized()) {
            Crashlytics.setString(KEY_USER_NAME, userName)
        }
    }
}