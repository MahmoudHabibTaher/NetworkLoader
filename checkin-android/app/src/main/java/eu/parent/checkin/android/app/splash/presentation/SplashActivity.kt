package eu.parent.checkin.android.app.splash.presentation

import android.app.Activity
import android.os.Bundle
import com.github.salomonbrys.kodein.factory
import eu.parent.android.app.common.presentation.actvities.BaseActivity
import eu.parent.android.app.common.presentation.viewmodels.BaseViewModel
import eu.parent.checkin.android.app.R
import eu.parent.checkin.android.app.splash.flow.SplashFlow

/**
 * Created by mahmoud on 9/8/17.
 */
class SplashActivity : BaseActivity() {
    private val viewModelFactory: (BaseActivity) -> SplashViewModel by factory()
    private val flowControllerFactory: (Activity) -> SplashFlow by factory()
    private lateinit var viewModel: SplashViewModel
    private lateinit var splashFlowController: SplashFlow

    override val baseViewModel: BaseViewModel?
        get() = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewModel = viewModelFactory(this)
        splashFlowController = flowControllerFactory(this)
    }

    override fun onResume() {
        super.onResume()


        compositeDisposable.add(viewModel.showHomeObservable.subscribe {
            splashFlowController.showHomeScreen()
        })

        compositeDisposable.add(viewModel.showLoginObservable.subscribe {
            splashFlowController.showLoginScreen()
        })

        loading_indicator_view.setLoading(true)

        viewModel.start()
    }
}