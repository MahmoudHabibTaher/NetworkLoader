package eu.parent.android.app.user.auth.login.presentation

import android.os.Bundle
import eu.parent.android.app.R
import eu.parent.android.app.common.presentation.actvities.BaseActivity
import eu.parent.android.app.common.presentation.fragments.BaseFragment
import eu.parent.android.app.common.presentation.viewmodels.BaseViewModel
import eu.parent.android.app.user.auth.check_activation_link_expired.presentation.CheckActivationLinkFragment
import eu.parent.android.app.user.auth.password.check.presentation.CheckResetLinkFragment

/**
 * Created by mahmoud on 9/8/17.
 */
class LoginActivity : BaseActivity() {

    companion object {

        val FRAGMENT_TO_LOAD: String = "fragmenttoLoad"
        val LOAD_CHECK_RESETPASS: String = "fragment_check_pass"
        val LOAD_CHECK_ACTIVATE_USER: String = "fragment_check_activate_user"
        val LINK: String = "link"
        val LOGIN_ACTIVITY_FRAGMENT_CONTAINER: Int = R.id.login_fragment_container

    }

    override val baseViewModel: BaseViewModel?
        get() = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initLoginFragment()
        checkFragmentToload()
    }

    fun checkFragmentToload() {

        if (intent.hasExtra(FRAGMENT_TO_LOAD)) {

            if (intent.getStringExtra(FRAGMENT_TO_LOAD).equals(LOAD_CHECK_RESETPASS)) {

                if (intent.hasExtra(LINK)) {
                    var link: String = intent.getStringExtra(LINK)
                    initCheckResetPasswordFragment(link, this@LoginActivity,
                            LoginActivity.LOGIN_ACTIVITY_FRAGMENT_CONTAINER)
                }
            } else if (intent.getStringExtra(FRAGMENT_TO_LOAD).equals(LOAD_CHECK_ACTIVATE_USER)) {
                if (intent.hasExtra(LINK)) {
                    var link: String = intent.getStringExtra(LINK)
                    initCheckActivationFragment(link, this@LoginActivity,
                            LoginActivity.LOGIN_ACTIVITY_FRAGMENT_CONTAINER)
                }
            } else {
                initLoginFragment()
            }

        } else {
            initLoginFragment()
        }


    }


    private fun initLoginFragment() {
        var fragment = supportFragmentManager.findFragmentById(R.id.login_fragment_container) as
                BaseFragment?
        if (fragment == null) {
            fragment = LoginFragment.newInstance()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.login_fragment_container, fragment)
                    .commit()
        }
    }

    private fun initCheckResetPasswordFragment(link: String, activity: BaseActivity, resorces: Int) {

        var fragment: BaseFragment? = supportFragmentManager.findFragmentById(resorces) as
                BaseFragment?
        if (fragment == null) {
            fragment = CheckResetLinkFragment.newInstance()
            var bundle: Bundle = Bundle()
            bundle.putString(CheckResetLinkFragment.LINK, link)
            fragment.arguments = bundle
            supportFragmentManager.beginTransaction()
                    .replace(resorces, fragment)
                    .commit()
        }
    }


    private fun initCheckActivationFragment(link: String, activity: BaseActivity, resorces: Int) {

        var fragment: BaseFragment? = supportFragmentManager.findFragmentById(resorces) as
                BaseFragment?
        if (fragment == null) {
            fragment = CheckActivationLinkFragment.newInstance()
            var bundle: Bundle = Bundle()
            bundle.putString(CheckActivationLinkFragment.LINK, link)
            fragment.arguments = bundle
            supportFragmentManager.beginTransaction()
                    .replace(resorces, fragment)
                    .commit()
        }
    }

    override fun onBackPressed() {

        var fragment = supportFragmentManager.findFragmentById(R.id.login_fragment_container) as
                BaseFragment?
        if (fragment!! is LoginFragment) {
            super.onBackPressed()
        } else {
            fragment = LoginFragment.newInstance()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.login_fragment_container, fragment)
                    .commit()
        }

    }

//
//    private fun initForgetPassFragment() {
//        var fragment = supportFragmentManager.findFragmentById(R.id.login_fragment_container) as
//                BaseFragment?
//        if (fragment == null) {
//            fragment = ForgetPasswordFragment.newInstance()
//            supportFragmentManager.beginTransaction()
//                    .replace(R.id.login_fragment_container, fragment)
//                    .commit()
//        }
//    }
//
//    private fun initForgetPassSuccessFragment() {
//        var fragment = supportFragmentManager.findFragmentById(R.id.login_fragment_container) as
//                BaseFragment?
//        if (fragment == null) {
//            fragment = ForgetPasswordSuccessFragment.newInstance()
//            supportFragmentManager.beginTransaction()
//                    .replace(R.id.login_fragment_container, fragment)
//                    .commit()
//        }
//    }
}