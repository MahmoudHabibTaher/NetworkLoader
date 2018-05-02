package eu.parent.android.app.common.presentation.viewmodels

import android.app.Application
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import com.parent.domain.permissions.IPermissionsManager
import com.parent.domain.auth.ISessionManager

/**
 * Created by mahmoud on 11/14/17.
 */
abstract class KodeinBaseViewModel(application: Application,
                                   override val kodein: Kodein) : BaseViewModel(application), KodeinAware {

    override val sessionManager: ISessionManager = instance()

    override val permissionsManager: IPermissionsManager = instance()
}