package com.bigo.networkloader.demo.core.domain.executors

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class BackgroundThreadExecutor : ThreadExecutor {
    override fun scheduler(): Scheduler =
        Schedulers.io()
}