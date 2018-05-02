package eu.parent.android.app

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import com.crashlytics.android.Crashlytics
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.android.autoAndroidModule
import com.parent.domain.DomainIntegration
import com.parent.domain.common.prefs.Prefs
import com.parent.domain.common.prefs.PrefsManager
import eu.parent.android.app.common.di.appModule
import eu.parent.android.app.common.presentation.actvities.BaseActivityLifeCycleCallbacks
import eu.parent.android.app.common.presentation.language.StringProvider
import eu.parent.android.app.domain.notifications.PushNotificationManager
import io.fabric.sdk.android.Fabric
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

/**
 * Created by mahmoud on 9/7/17.
 */
class App : Application(), KodeinAware {
    companion object {
        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }

    override val kodein by Kodein.lazy {
        import(autoAndroidModule(this@App))
        bind<Prefs>() with singleton { PrefsManager(this@App) }
        import(appModule)
    }

    override fun onCreate() {
        super.onCreate()
        CalligraphyConfig.initDefault(calligraphyDefaultConfig())
        initFabric()
        DomainIntegration.with(this)
        initPusNotificationsManager()
        registerActivityLifecycleCallbacks(BaseActivityLifeCycleCallbacks())
        StringProvider.init(this)
    }

    private fun calligraphyDefaultConfig() = CalligraphyConfig.Builder()
            .setDefaultFontPath("fonts/proximanova-regular-webfont.ttf")
            .setFontAttrId(R.attr.fontPath)
            .build()


    private fun initFabric() {
        if (BuildConfig.FABRIC_ENABLED) {
            Fabric.with(this, Crashlytics())
        }
    }


    private fun initPusNotificationsManager() {
        val lazyKodein = LazyKodein(appKodein)
        PushNotificationManager.initPushNotification(this@App, lazyKodein)
    }

}