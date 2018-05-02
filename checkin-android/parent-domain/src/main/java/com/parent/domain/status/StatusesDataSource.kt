package com.parent.domain.status

import com.parent.entities.*
import io.reactivex.Single

/**
 * Created by mahmoud on 9/19/17.
 */
interface StatusesDataSource {

    fun getServerTime(): Single<String>

    fun reportChildrenSleepWakeup(request: ReportChildSleepWakeUpRequestModel): Single<String>

    fun getInstitutionOpeningHours(institutionId: String): Single<List<OpeningHourDayModel>>

    fun reportChildrenDiaperToilet(request: List<ReportChildToiletDiaperItemModel>): Single<String>

    fun reportChildrenMood(request: List<ReportChildMoodItemModel>): Single<String>
    fun reportChildrenMeal(request: List<ReportChildMealItemModel>): Single<String>

    fun checkIn(childrenIds: List<String>, staffIds: List<String>, instituteId: String): Single<String>

    fun checkOut(childrenIds: List<String>, staffIds: List<String>, instituteId: String): Single<String>

    fun reportLeave(children: List<String>, staff: List<String>, startDate: Long, endDate: Long,
                    note: String, type: String, instituteId: String): Single<String>

    fun reportTrip(request: ReportTripRequest, instituteId: String): Single<String>

    fun reportBus(request: ReportBusRequest, instituteId: String): Single<String>

    fun reportMeeting(request: ReportMeetingRequest, instituteId: String): Single<String>

    fun getInstitutionMeal(institutionId: String): Single<List<InstituteMeal>>

}