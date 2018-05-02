package eu.parent.android.app.common.presentation.dialogs

import android.os.Bundle
import android.support.v4.app.DialogFragment
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.android.SupportFragmentInjector

/**
 * Created by mahmoud on 9/27/17.
 */
abstract class KodeinDialogFragment : DialogFragment(), SupportFragmentInjector {
    override val injector: KodeinInjector = KodeinInjector()

    override fun onCreate(savedInstanceState: Bundle?) {
        initializeInjector()
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        destroyInjector()
        super.onDestroy()
    }
}