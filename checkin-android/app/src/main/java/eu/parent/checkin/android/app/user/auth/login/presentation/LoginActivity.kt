package eu.parent.checkin.android.app.user.auth.login.presentation

import android.os.Bundle
import eu.parent.android.app.common.presentation.actvities.BaseActivity
import eu.parent.android.app.common.presentation.fragments.BaseFragment
import eu.parent.android.app.common.presentation.viewmodels.BaseViewModel
import eu.parent.checkin.android.app.R

/**
 * Created by mahmoud on 9/8/17.
 */
class LoginActivity : BaseActivity() {
    override val baseViewModel: BaseViewModel?
        get() = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initLoginFragment()
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
}