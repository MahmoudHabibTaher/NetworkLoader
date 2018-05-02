package com.parent.domain.children.pickup

import com.parent.domain.base.BaseCompletableUseCase
import com.parent.domain.base.Params
import com.parent.domain.children.data.ChildrenDataSource
import com.parent.domain.children.ParamsConstants
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import io.reactivex.Completable

/**
 * Created by mahmoud on 12/6/17.
 */
class addChildPickupInfo(threadExecutor: ThreadExecutor,
                         postThreadExecutor: PostThreadExecutor,
                         private val childrenDataSource: ChildrenDataSource) :
        BaseCompletableUseCase(threadExecutor, postThreadExecutor) {
    override fun buildUseCaseCompletable(params: Params): Completable =
            childrenDataSource.addChildPickUpInfo(params.get(ParamsConstants.CHILD_ID), params.get(ParamsConstants.CHILD_PICK_UP))

}