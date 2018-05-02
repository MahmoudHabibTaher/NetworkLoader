package com.parent.domain.staff

import com.parent.entities.InstituteStaffModel
import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.Params
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import io.reactivex.Single

/**
 * Created by mahmoud on 11/28/17.
 */
class LoadInstituteStaffList(threadExecutor: ThreadExecutor,
                             postThreadExecutor: PostThreadExecutor,
                             private val nationalityDataSource: InstituteStaffDataSource) :
        BaseSingleUseCase<List<InstituteStaffModel>>(threadExecutor, postThreadExecutor) {
    override fun buildUseCaseSingle(params: Params): Single<List<InstituteStaffModel>> =
            nationalityDataSource.loadInstituteStaff(params.get(ParamsConstants.INST_ID))
}