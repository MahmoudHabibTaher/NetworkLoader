package com.bigo.networkloader.demo.core.domain.di

import com.bigo.networkloader.demo.core.domain.executors.BackgroundThreadExecutor
import com.bigo.networkloader.demo.core.domain.executors.PostThreadExecutor
import com.bigo.networkloader.demo.core.domain.executors.ThreadExecutor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

val coreDomainModule = Kodein.Module("coreDomainModule") {
    bind<ThreadExecutor>("workThreadExecutor") with provider {
        BackgroundThreadExecutor()
    }

    bind<ThreadExecutor>("postWorkThreadExecutor") with provider {
        PostThreadExecutor()
    }
}