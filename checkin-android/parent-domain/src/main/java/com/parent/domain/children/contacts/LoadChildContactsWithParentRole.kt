package com.parent.domain.children.contacts

import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.Params
import com.parent.domain.children.ParamsConstants
import com.parent.domain.children.contacts.data.ChildContactsDataSource
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import com.parent.entities.ChildContactModel
import io.reactivex.Single

/**
 * Created by mahmoud on 12/6/17.
 */
class LoadChildContactsWithParentRole(threadExecutor: ThreadExecutor,
                                      postThreadExecutor: PostThreadExecutor,
                                      private val childContactsDataSource: ChildContactsDataSource,
                                      private val childContactStateObservable: IChildContactStateObservable) :
        BaseSingleUseCase<List<ChildContactModel>>(threadExecutor, postThreadExecutor) {
    override fun buildUseCaseSingle(params: Params): Single<List<ChildContactModel>> {

        var page: Int = params.get(ParamsConstants.PAGE, 1)
        return childContactsDataSource.loadChildContactsWithParentRole(params.get(ParamsConstants.CHILD_ID),
                page)
    }


}