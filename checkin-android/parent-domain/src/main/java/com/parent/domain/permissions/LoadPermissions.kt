package com.parent.domain.permissions

import com.parent.entities.PermissionsGroup
import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.Params
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import io.reactivex.Single

/**
 * Created by mahmoud on 9/26/17.
 */
class LoadPermissions(threadExecutor: ThreadExecutor,
                      postThreadExecutor: PostThreadExecutor,
                      private val permissionsDataSource: PermissionsDataSource) :
        BaseSingleUseCase<List<PermissionsGroup>>(threadExecutor, postThreadExecutor) {
    override fun buildUseCaseSingle(params: Params): Single<List<PermissionsGroup>> =
            permissionsDataSource.loadPermissions()
}