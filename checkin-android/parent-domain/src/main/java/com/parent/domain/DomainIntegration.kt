package com.parent.domain

import android.app.Application
import com.parent.domain.realm.base.RealmManager
import java.lang.ref.WeakReference

/**
 * a class that is responsible for integrating the use-cases layer with the application
 *
 * Created by Ahmed Adel Ismail on 2/13/2018.
 */
object DomainIntegration {

    private lateinit var application: WeakReference<Application>

    fun with(application: Application) {
        DomainIntegration.application = WeakReference(application)
        RealmManager.initRealm(application)
    }

    /**
     * get the [Application] that holds the presentation layer for this domain layer
     */
    fun getApplication(): Application = this.application.get()!!

}

