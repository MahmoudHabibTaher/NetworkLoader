package com.bigo.networkloader.demo.pins.list.domain.di

import com.bigo.networkloader.demo.core.domain.Params
import com.bigo.networkloader.demo.core.domain.SingleUseCase
import com.bigo.networkloader.demo.pins.list.data.di.pinsDataModule
import com.bigo.networkloader.demo.pins.list.domain.LoadPins
import com.bigo.networkloader.demo.pins.list.domain.entities.Pin
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

val pinsDomainModule = Kodein.Module("pinsDomainModule") {
    import(pinsDataModule)

    bind<SingleUseCase<List<Pin>, Params>>("loadPinsUseCase") with provider {
        LoadPins(
            instance("workThreadExecutor"), instance("postWorkThreadExecutor"),
            instance("pinsDataSource")
        )
    }
}