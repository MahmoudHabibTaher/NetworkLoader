package com.bigo.networkloader.demo

import android.app.Application
import com.bigo.networkloader.demo.core.domain.di.coreDomainModule
import com.bigo.networkloader.demo.core.network.di.networkModule
import com.bigo.networkloader.demo.pins.list.domain.di.pinsDomainModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule

class PinsApplication : Application(), KodeinAware {
    override val kodein: Kodein by Kodein.lazy {
        import(androidXModule(this@PinsApplication))
        import(networkModule)
        import(coreDomainModule)
        import(pinsDomainModule)
    }
}