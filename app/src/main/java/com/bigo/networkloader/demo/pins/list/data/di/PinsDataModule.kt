package com.bigo.networkloader.demo.pins.list.data.di

import com.bigo.networkloader.demo.pins.list.data.PinsDataSource
import com.bigo.networkloader.demo.pins.list.data.remote.di.pinsRemoteDataModule
import com.bigo.networkloader.demo.pins.list.domain.PinsRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val pinsDataModule = Kodein.Module("pinsDataModule") {
    import(pinsRemoteDataModule)

    bind<PinsRepository>("pinsDataSource") with singleton {
        PinsDataSource(instance("pinsRemoteDataSource"))
    }
}