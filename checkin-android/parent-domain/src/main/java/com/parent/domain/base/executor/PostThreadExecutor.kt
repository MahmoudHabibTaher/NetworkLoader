package com.parent.domain.executor

import io.reactivex.Scheduler

/**
 * Created by mahmoud on 6/1/17.
 */
interface PostThreadExecutor {
    val scheduler: Scheduler
}
