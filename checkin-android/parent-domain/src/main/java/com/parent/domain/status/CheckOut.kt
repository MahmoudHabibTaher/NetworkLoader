package com.parent.domain.status

import com.parent.domain.auth.ISessionManager
import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.Params
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import com.parent.domain.location.LocationHelper
import com.parent.entities.InstitutionModel
import com.parent.entities.exceptions.LocationOutOfRangeException
import com.parent.entities.exceptions.MissingInstitueLocationInfoException
import io.reactivex.Single

/**
 * Created by mahmoud on 1/3/18.
 */
class CheckOut(threadExecutor: ThreadExecutor,
               postThreadExecutor: PostThreadExecutor,
               private val sessionManager: ISessionManager,
               private val statusesDataSource: StatusesDataSource,
               private val locationHelper: LocationHelper,
               private val statusStatePublisher: IStatusUpdatedPublisher) :
        BaseSingleUseCase<String>(threadExecutor, postThreadExecutor) {
    override fun buildUseCaseSingle(params: Params): Single<String> =
            sessionManager.getLoggedInUser().flatMap { user ->
                sessionManager.getCurrentInstitute().flatMap {
                    val childrenIds = params.get<List<String>>(CheckInOutParams.CHILDREN_IDS)
                    val staffIds = params.get<List<String>>(CheckInOutParams.STAFF_IDS)
                    val instituteId = it.id
                    if (user.isContact) {
                        val instituteLocationInfoAvailable = checkInstituteLocationInfo(it)

                        if (instituteLocationInfoAvailable) {

                            val instituteLat = it.latitude.toDouble()
                            val instituteLng = it.longitude.toDouble()
                            val checkInDiameter = it.checkInDiameter.toDouble()

                            val userLat = params.get<Double>(CheckInOutParams.LATITUDE)
                            val userLng = params.get<Double>(CheckInOutParams.LONGITUDE)

                            val inRange = locationHelper.isLocationInRange(instituteLat, instituteLng,
                                    userLat, userLng, checkInDiameter)

                            if (inRange) {
                                statusesDataSource.checkOut(childrenIds, staffIds,
                                        instituteId)
                            } else {
                                Single.error(LocationOutOfRangeException())
                            }
                        } else Single.error(MissingInstitueLocationInfoException())
                    } else {
                        statusesDataSource.checkOut(childrenIds, staffIds,
                                instituteId)
                    }
                }.doOnSuccess {
                    statusStatePublisher.notifyStatusUpdated()
                }
            }

    private fun checkInstituteLocationInfo(institute: InstitutionModel): Boolean =
            institute.latitude.toDoubleOrNull() != null &&
                    institute.longitude.toDoubleOrNull() != null &&
                    institute.checkInDiameter.toDoubleOrNull() != null
}