package com.parent.domain.status

import com.parent.entities.*
import io.reactivex.Single

/**
 * Created by mahmoud on 9/19/17.
 */
class StatusesRepository(private val remoteDataSource: StatusesDataSource,
                         private val localDataSource: StatusesDataSource) : StatusesDataSource {
    override fun reportChildrenMeal(request: List<ReportChildMealItemModel>): Single<String> = remoteDataSource.reportChildrenMeal(request)


    override fun getInstitutionMeal(institutionId: String): Single<List<InstituteMeal>> =
        remoteDataSource.getInstitutionMeal(institutionId)


    override fun reportChildrenMood(request: List<ReportChildMoodItemModel>): Single<String> = remoteDataSource.reportChildrenMood(request)
    override fun getServerTime(): Single<String> =
            remoteDataSource.getServerTime()

    override fun reportChildrenSleepWakeup(request: ReportChildSleepWakeUpRequestModel): Single<String> =
            remoteDataSource.reportChildrenSleepWakeup(request)

    override fun getInstitutionOpeningHours(institutionId: String): Single<List<OpeningHourDayModel>> =
            remoteDataSource.getInstitutionOpeningHours(institutionId)

    override fun reportChildrenDiaperToilet(request: List<ReportChildToiletDiaperItemModel>): Single<String> =
            remoteDataSource.reportChildrenDiaperToilet(request)

    override fun checkIn(childrenIds: List<String>, staffIds: List<String>, instituteId: String): Single<String> =
            remoteDataSource.checkIn(childrenIds, staffIds, instituteId)

    override fun checkOut(childrenIds: List<String>, staffIds: List<String>, instituteId: String): Single<String> =
            remoteDataSource.checkOut(childrenIds, staffIds, instituteId)

    override fun reportLeave(children: List<String>, staff: List<String>, startDate: Long,
                             endDate: Long, note: String, type: String, instituteId: String): Single<String> =
            remoteDataSource.reportLeave(children, staff, startDate, endDate, note, type, instituteId)

    override fun reportTrip(request: ReportTripRequest, instituteId: String): Single<String> =
            remoteDataSource.reportTrip(request, instituteId)

    override fun reportBus(request: ReportBusRequest, instituteId: String): Single<String> =
            remoteDataSource.reportBus(request, instituteId)

    override fun reportMeeting(request: ReportMeetingRequest, instituteId: String): Single<String> =
            remoteDataSource.reportMeeting(request, instituteId)
}