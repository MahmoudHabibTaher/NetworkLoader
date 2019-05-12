package eu.parent.checkin.android.app.splash.di

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.factory
import com.github.salomonbrys.kodein.instance
import eu.parent.checkin.android.app.splash.flow.SplashFlow
import eu.parent.checkin.android.app.splash.flow.SplashFlowController
import eu.parent.checkin.android.app.splash.presentation.SplashViewModel
import eu.parent.checkin.android.app.splash.presentation.SplashViewModelFactory

/**
 * Created by mahmoud on 9/22/17.
 */
val splashModule = Kodein.Module {
    bind<SplashViewModel>() with factory { activity: FragmentActivity ->
        ViewModelProviders.of(activity, SplashViewModelFactory(instance(), kodein, instance("isUserLoggedInUseCase"))).get(SplashViewModel::class.java)
    }

    bind<SplashFlow>() with factory { activity: Activity ->
        SplashFlowController(activity)
    }
}