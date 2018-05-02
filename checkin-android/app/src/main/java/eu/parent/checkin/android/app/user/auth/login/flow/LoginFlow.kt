package eu.parent.android.app.user.auth.login.flow

/**
 * Created by mahmoud on 9/11/17.
 */
interface LoginFlow {
    fun showHomeScreen()
    fun showForgetPassword()
    fun showRequestActivationLinkScreen(email: String)
}