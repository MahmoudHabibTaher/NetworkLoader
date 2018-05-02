package com.parent.domain.status

import com.parent.entities.*
import io.reactivex.Single

/**
 * Created by mahmoud on 9/19/17.
 */
class StatusesLocalDataSource() : StatusesDataSource {
    override fun reportChildrenMeal(request: List<ReportChildMealItemModel>): Single<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getInstitutionMeal(institutionId: String): Single<List<InstituteMeal>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun reportChildrenMood(request: List<ReportChildMoodItemModel>): Single<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun reportChildrenDiaperToilet(request: List<ReportChildToiletDiaperItemModel>): Single<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getInstitutionOpeningHours(institutionId: String): Single<List<OpeningHourDayModel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getServerTime(): Single<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun reportChildrenSleepWakeup(request: ReportChildSleepWakeUpRequestModel): Single<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun reportLeave(children: List<String>, staff: List<String>, startDate: Long, endDate: Long, note: String, type: String, instituteId: String): Single<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun checkIn(childrenIds: List<String>, staffIds: List<String>, instituteId: String): Single<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun checkOut(childrenIds: List<String>, staffIds: List<String>, instituteId: String): Single<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun reportTrip(request: ReportTripRequest, instituteId: String): Single<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun reportBus(request: ReportBusRequest, instituteId: String): Single<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun reportMeeting(request: ReportMeetingRequest, instituteId: String): Single<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}