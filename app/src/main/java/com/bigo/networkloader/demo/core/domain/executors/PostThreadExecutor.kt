package com.bigo.networkloader.demo.core.domain.executors

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class PostThreadExecutor : ThreadExecutor {
    override fun scheduler(): Scheduler =
        AndroidSchedulers.mainThread()
}