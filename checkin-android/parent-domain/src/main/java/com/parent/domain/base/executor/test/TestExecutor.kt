package com.parent.domain.executor.test

import com.parent.domain.executor.ThreadExecutor
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Created by mahmoud on 9/11/17.
 */
class TestExecutor : ThreadExecutor {
    override val scheduler: Scheduler = Schedulers.single()
}