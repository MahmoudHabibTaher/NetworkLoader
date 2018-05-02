package com.parent.domain.institutions

import com.parent.entities.TimeFormat
import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.Params
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import io.reactivex.Single

/**
 * Created by mahmoud on 12/2/17.
 */
class LoadTimeFormats(threadExecutor: ThreadExecutor,
                      postThreadExecutor: PostThreadExecutor,
                      private val instituteOptionsDataSource: InstituteOptionsDataSource) :
        BaseSingleUseCase<List<TimeFormat>>(threadExecutor, postThreadExecutor) {
    override fun buildUseCaseSingle(params: Params): Single<List<TimeFormat>> =
            instituteOptionsDataSource.loadTimeFormats()
}