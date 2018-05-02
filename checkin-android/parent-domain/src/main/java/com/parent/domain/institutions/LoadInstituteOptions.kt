package com.parent.domain.institutions

import com.parent.entities.InstituteOptions
import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.CommonParams
import com.parent.domain.base.Params
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import com.parent.domain.permissions.Permissions
import com.parent.domain.permissions.IPermissionsManager
import io.reactivex.Single
import io.reactivex.functions.BiFunction

/**
 * Created by mahmoud on 12/2/17.
 */
class LoadInstituteOptions(threadExecutor: ThreadExecutor,
                           postThreadExecutor: PostThreadExecutor,
                           private val instituteOptionsDataSource: InstituteOptionsDataSource,
                           private val checkPermissionsGranted: IPermissionsManager) :
        BaseSingleUseCase<InstituteOptions>(threadExecutor, postThreadExecutor) {
    override fun buildUseCaseSingle(params: Params): Single<InstituteOptions> {
        val forceUpdate = params.get(CommonParams.FORCE_UPDATE, false)
        if (forceUpdate) {
            instituteOptionsDataSource.invalidateCache()
        }
        return Single.zip(instituteOptionsDataSource.loadInstituteOptions(params.get(ParamsConstants.INSTITUTE_ID)),
                checkCanEdit(), BiFunction { options, canEdit ->
            options.canEdit = canEdit
            options
        })
    }

    private fun checkCanEdit(): Single<Boolean> =
            checkPermissionsGranted.isPermissionsGranted(listOf(Permissions.InstituteOptions.EDIT_INSTITUTE_DETAILS))
                    .flatMap {
                        Single.just(it[Permissions.InstituteOptions.EDIT_INSTITUTE_DETAILS] ?: false)
                    }
}