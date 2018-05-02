package eu.parent.android.app.user.auth.login.flow

import android.support.annotation.IdRes
import eu.parent.android.app.common.presentation.actvities.BaseActivity
import eu.parent.android.app.common.presentation.fragments.BaseFragment
import eu.parent.android.app.main.presentation.MainActivity
import eu.parent.android.app.user.auth.forgot_password.presentation.ForgetPasswordFragment
import eu.parent.android.app.user.auth.request_activation_link.presentation.RequestActivationLinkFragment
import org.jetbrains.anko.startActivity

/**
 * Created by mahmoud on 9/11/17.
 */
class LoginFlowController(private val activity: BaseActivity, @IdRes private val fragmentContainerResId: Int) : LoginFlow {
    override fun showForgetPassword() {
        var fragment = activity.supportFragmentManager.findFragmentById(fragmentContainerResId) as BaseFragment?
        if (fragment != null && fragment is ForgetPasswordFragment) {
            fragment.refresh()
        } else {
            fragment = ForgetPasswordFragment.newInstance()
            activity.supportFragmentManager.beginTransaction()
                    .replace(fragmentContainerResId, fragment)
                    .commit()
        }
    }

    override fun showHomeScreen() {
        activity.startActivity<MainActivity>()
        activity.finish()
    }

    override fun showRequestActivationLinkScreen(email: String) {
        var fragment = activity.supportFragmentManager.findFragmentById(fragmentContainerResId) as BaseFragment?
        if (fragment != null && fragment is RequestActivationLinkFragment) {
            fragment.refresh()
        } else {
            fragment = RequestActivationLinkFragment.newInstance(email, RequestActivationLinkFragment.TYPE_INACTIVE_LOGIN)
            activity.supportFragmentManager.beginTransaction()
                    .replace(fragmentContainerResId, fragment)
                    .commit()
        }
    }
}