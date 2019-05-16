package com.bigo.networkloader.demo.pins.list.presentation.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.bigo.networkloader.demo.pins.list.presentation.PinsListViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.factory
import org.kodein.di.generic.instance

val pinsPresentationModule = Kodein.Module("pinsPresentationModule") {
    bind<PinsListViewModel>() with factory { activity: AppCompatActivity ->
        ViewModelProviders.of(
            activity, PinsListViewModel.Factory(
                instance(),
                instance("loadPinsUseCase")
            )
        ).get(PinsListViewModel::class.java)
    }
}