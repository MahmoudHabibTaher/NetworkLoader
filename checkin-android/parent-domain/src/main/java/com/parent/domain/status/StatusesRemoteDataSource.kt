package com.parent.domain.status

import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.base.mappers.mapFromWith
import com.parent.domain.base.mappers.mapToWith
import com.parent.domain.common.data.source.remote.BaseRemoteDataSource
import com.parent.domain.datetime.IDateTimeConverter
import com.parent.entities.*
import io.reactivex.Single

/**
 * Created by mahmoud on 9/19/17.
 */
class StatusesRemoteDataSource(private val statusesApi: StatusesApi,
                               private val sleepWakeupRequestModelMapper: ModelMapper<ReportChildSleepWakeUpRequestModelRemote,
                                       ReportChildSleepWakeUpRequestModel>,
                               private val toilerDiaperRequestModelMapper: ModelMapper<ReportChildToiletDiaperItemModelRemote,
                                       ReportChildToiletDiaperItemModel>,
                               private val openingHoursModelMapper: ModelMapper<OpeningHourDayModelRemote, OpeningHourDayModel>,
                               private val dateTimeConverter: IDateTimeConverter,
                               private val moodRequestModelMapper: ModelMapper<ReportChildMoodItemModelRemote, ReportChildMoodItemModel>,
                               private val instituteMealModelMapper:ModelMapper<InstituteMealRemote,InstituteMeal>,
                               private val mealRequestModelMapper: ModelMapper<ReportChildMealItemModelRemote, ReportChildMealItemModel>
                               )
    : StatusesDataSource, BaseRemoteDataSource() {

    override fun reportChildrenMeal(request: List<ReportChildMealItemModel>): Single<String> =
            callSingle(statusesApi.reportChildrenMeal(request.mapToWith(mealRequestModelMapper)).flatMap {
                val message = it.message ?: ""
                Single.just(message)
            })


    override fun getInstitutionMeal(institutionId: String): Single<List<InstituteMeal>> =
            callSingle(statusesApi.getInstituteMeal(institutionId).flatMap {
                mapMeals(it.data!!)

            })




    override fun reportChildrenMood(request: List<ReportChildMoodItemModel>): Single<String> =
            callSingle(statusesApi.reportChildrenMood(request.mapToWith(moodRequestModelMapper)).flatMap {
                val message = it.message ?: ""
                Single.just(message)
            })

    override fun getServerTime(): Single<String> =
            callSingle(statusesApi.getServerTime().flatMap {
                val data = it.data ?: ServerDateModelRemote()
                Single.just(data?.datetime ?: "")
            })

    override fun reportChildrenSleepWakeup(request: ReportChildSleepWakeUpRequestModel): Single<String> =
            callSingle(statusesApi.reportChildrenSleepWakeup(request.mapToWith(sleepWakeupRequestModelMapper)).flatMap {
                val message = it.message ?: ""
                Single.just(message)
            })

    override fun getInstitutionOpeningHours(institutionId: String): Single<List<OpeningHourDayModel>> =
            callSingle(statusesApi.getInstitutionOpeningHours(institutionId).flatMap {
                val data = it.data ?: listOf()
                Single.just(data.mapFromWith(openingHoursModelMapper))
            })

    override fun reportChildrenDiaperToilet(request: List<ReportChildToiletDiaperItemModel>): Single<String> =
            callSingle(statusesApi.reportChildrenDiaperToilet(request.mapToWith(toilerDiaperRequestModelMapper)).flatMap {
                val message = it.message ?: ""
                Single.just(message)
            })

    override fun checkIn(childrenIds: List<String>, staffIds: List<String>, instituteId: String): Single<String> =
            callSingle(statusesApi.checkIn(buildCheckInOutRequtes(childrenIds, staffIds, instituteId)).flatMap {
                Single.just(it.message ?: "")
            })

    override fun checkOut(childrenIds: List<String>, staffIds: List<String>, instituteId: String): Single<String> =
            callSingle(statusesApi.checkOut(buildCheckInOutRequtes(childrenIds, staffIds, instituteId)).flatMap {
                Single.just(it.message ?: "")
            })

    private fun buildCheckInOutRequtes(children: List<String>, staff: List<String>, instituteId: String) =
            CheckInOutRemoteRequest.Builder()
                    .children(children)
                    .staff(staff)
                    .instituteId(instituteId)
                    .build()

    override fun reportLeave(children: List<String>, staff: List<String>, startDate: Long,
                             endDate: Long, note: String, type: String, instituteId: String): Single<String> =
            callSingle(statusesApi.reportLeave(buildLeaveRequest(children, staff, startDate,
                    endDate, note, type, instituteId)).flatMap {
                Single.just(it.message
                        ?: "")
            })

    override fun reportTrip(request: ReportTripRequest, instituteId: String): Single<String> =
            callSingle(statusesApi.reportTrip(buildTripRequest(request, instituteId)).flatMap {
                Single.just(it.message ?: "")
            })

    override fun reportBus(request: ReportBusRequest, instituteId: String): Single<String> =
            callSingle(statusesApi.reportBus(buildBusRequest(request, instituteId)).flatMap {
                Single.just(it.message ?: "")
            })

    override fun reportMeeting(request: ReportMeetingRequest, instituteId: String): Single<String> =
            callSingle(statusesApi.reportMeeting(buildMeetingRequest(request, instituteId)).flatMap {
                Single.just(it.message ?: "")
            })

    private fun buildLeaveRequest(children: List<String>, staff: List<String>, startDate: Long,
                                  endDate: Long, note: String, type: String, instituteId: String) =
            ReportLeaveRemoteRequest.Builder().children(children).staff(staff)
                    .startDate(formatToServerDate(startDate))
                    .endDate(formatToServerDate(endDate))
                    .note(note)
                    .type(type)
                    .instituteId(instituteId)
                    .build()

    private fun buildTripRequest(request: ReportTripRequest, instituteId: String) =
            ReportTripRemoteRequest.Builder().instituteId(instituteId)
                    .items(request.items.map {
                        ReportTripRemoteItem.Builder()
                                .id(it.id)
                                .onTrip(it.onTrip)
                                .time(if (it.isNow == true) it.serverTime else
                                    formatToServerTime(it.time))
                                .type(it.type)
                                .build()
                    }).build()

    private fun buildBusRequest(request: ReportBusRequest, instituteId: String) =
            ReportBusRemoteRequest.Builder().instituteId(instituteId)
                    .items(request.items.map {
                        ReportBusRemoteItem.Builder()
                                .id(it.id)
                                .onBus(it.onBus)
                                .time(if (it.isNow == true) it.serverTime else
                                    formatToServerTime(it.time))
                                .type(it.type)
                                .build()
                    }).build()

    private fun buildMeetingRequest(request: ReportMeetingRequest, instituteId: String) =
            ReportMeetingRemoteRequest.Builder().instituteId(instituteId)
                    .items(request.items.map {
                        ReportMeetingRemoteItem.Builder()
                                .id(it.id)
                                .onMeeting(it.onMeeting)
                                .time(if (it.isNow == true) it.serverTime else
                                    formatToServerTime(it.time))
                                .type(it.type)
                                .build()
                    }).build()

    private fun formatToServerDate(date: Long) = dateTimeConverter.convertToServerDate(date)
            ?: ""

    private fun formatToServerTime(date: Long) = dateTimeConverter.convertToServerTime(date) ?: ""

    private fun mapMeals(data: List<InstituteMealRemote>?) =
            Single.just(data?.mapFromWith(instituteMealModelMapper) ?: emptyList())

}