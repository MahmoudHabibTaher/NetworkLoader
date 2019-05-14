package com.bigo.networkloader.demo.core.domain.executors

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestThreadExecutor : ThreadExecutor {
    override fun scheduler(): Scheduler =
        Schedulers.single()
}