package eu.parent.android.app.splash.flow

import android.app.Activity
import eu.parent.android.app.main.presentation.MainActivity
import eu.parent.android.app.user.auth.login.presentation.LoginActivity
import org.jetbrains.anko.startActivity

/**
 * Created by mahmoud on 9/8/17.
 */
class SplashFlowController(private val activity: Activity) : SplashFlow {
    override fun showHomeScreen() {
        activity.startActivity<MainActivity>()
        activity.finish()
    }

    override fun showLoginScreen() {
        activity.startActivity<LoginActivity>()
        activity.finish()
    }
}