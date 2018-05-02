package com.parent.domain.executor.test

import com.parent.domain.executor.PostThreadExecutor
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Created by mahmoud on 9/11/17.
 */
class TestPostExecutor : PostThreadExecutor {
    override val scheduler: Scheduler = Schedulers.single()
}