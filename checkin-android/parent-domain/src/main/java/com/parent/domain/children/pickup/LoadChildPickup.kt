package com.parent.domain.children.pickup

import com.parent.entities.ChildPickupModel
import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.Params
import com.parent.domain.children.data.ChildrenDataSource
import com.parent.domain.children.ParamsConstants
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import io.reactivex.Single

/**
 * Created by mahmoud on 12/6/17.
 */
class LoadChildPickup(threadExecutor: ThreadExecutor,
                      postThreadExecutor: PostThreadExecutor,
                      private val childrenDataSource: ChildrenDataSource) :
        BaseSingleUseCase<ChildPickupModel>(threadExecutor, postThreadExecutor) {
    override fun buildUseCaseSingle(params: Params): Single<ChildPickupModel> =
            childrenDataSource.loadChildPickUp(params.get(ParamsConstants.CHILD_ID))

}