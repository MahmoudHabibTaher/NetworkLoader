package com.parent.domain.children.contacts

import com.parent.entities.ChildContactRelationModel
import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.Params
import com.parent.domain.children.contacts.data.ChildContactsDataSource
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import io.reactivex.Single

/**
 * Created by mahmoud on 12/6/17.
 */
class LoadChildContactRelations(threadExecutor: ThreadExecutor,
                                postThreadExecutor: PostThreadExecutor,
                                private val childContactsDataSource: ChildContactsDataSource) :
        BaseSingleUseCase<List<ChildContactRelationModel>>(threadExecutor, postThreadExecutor) {
    override fun buildUseCaseSingle(params: Params): Single<List<ChildContactRelationModel>> =
            childContactsDataSource.loadChildContactsRelations()

}