package eu.parent.checkin.android.app.user.auth.login.flow

import eu.parent.android.app.common.presentation.actvities.BaseActivity
import eu.parent.checkin.android.app.main.presentation.MainActivity
import org.jetbrains.anko.startActivity

/**
 * Created by mahmoud on 9/11/17.
 */
class LoginFlowController(private val activity: BaseActivity) : LoginFlow {
    override fun showHomeScreen() {
        activity.startActivity<MainActivity>()
        activity.finish()
    }
}