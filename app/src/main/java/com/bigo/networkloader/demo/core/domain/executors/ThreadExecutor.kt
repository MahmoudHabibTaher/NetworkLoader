package com.bigo.networkloader.demo.core.domain.executors

import io.reactivex.Scheduler

interface ThreadExecutor {
    fun scheduler(): Scheduler
}