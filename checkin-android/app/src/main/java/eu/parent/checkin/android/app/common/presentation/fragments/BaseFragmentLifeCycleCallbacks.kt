package eu.parent.android.app.common.presentation.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.parent.domain.common.log.logInfo
import eu.parent.android.app.common.debug.DebugHelper
import eu.parent.android.app.common.presentation.interfaces.DisposablesHolder
import eu.parent.android.app.common.presentation.interfaces.clearDisposables
import eu.parent.android.app.common.presentation.interfaces.disposeAll

/**
 *  a class that is responsible for handling common operations during Fragment LifeCycle
 *
 * Created by Ahmed Adel Ismail on 2/24/2018.
 */
class BaseFragmentLifeCycleCallbacks : FragmentManager.FragmentLifecycleCallbacks() {

    override fun onFragmentCreated(fm: FragmentManager?, fragment: Fragment?, savedInstanceState: Bundle?) {
        super.onFragmentCreated(fm, fragment, savedInstanceState)
        logInfo("${fragment!!::class.java.simpleName} created")
        DebugHelper.setCurrentScreen(fragment::class.java.simpleName)
    }

    override fun onFragmentPaused(fm: FragmentManager?, fragment: Fragment?) {
        super.onFragmentPaused(fm, fragment)

        fragment.takeIf { it is DisposablesHolder }
                .let { it as? DisposablesHolder }
                .also { it?.clearDisposables() }
    }


    override fun onFragmentDestroyed(fm: FragmentManager?, fragment: Fragment?) {
        super.onFragmentDestroyed(fm, fragment)
        logInfo("${fragment!!::class.java.simpleName} destroyed")

        fragment.takeIf { it is DisposablesHolder }
                .let { it as? DisposablesHolder }
                .also { it?.disposeAll() }

    }
}