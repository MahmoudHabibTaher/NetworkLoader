package com.parent.domain.realm.entities

import com.parent.entities.IBuilder
import io.realm.RealmList
import io.realm.RealmObject


/**
 * Created by mahmoud on 10/24/17.
 */
open class CalendarDataRealm(
        var instId: String = "",
        var userId: String = "",
        var week: String = "",
        var year: String = "",
        var currentDay: String = "",
        var startDate: String = "",
        var startDay: String = "",
        var endDay: String = "",
        var weekDayResponsRealms: RealmList<WeekDaysRealm> = RealmList()) : RealmObject() {

    class TestBuilder : IBuilder<CalendarDataRealm> {
        companion object {
            private val INSTID = listOf("1", "2", "3", "4")
            private val USERID = listOf("1", "2", "3", "4")
            private val WEEKS = listOf("1", "2", "3", "4")
            private val YEARS = listOf("2016", "2017", "2018", "2019")
            private val START_DATES = listOf("4/1/2017 00:00:00", "11/1/2017 00:00:00", "18/1/2017 00:00:00", "25/1/2017 00:00:00")
            private val START_DAYS = listOf("4/1/2017 00:00:00", "11/1/2017 00:00:00", "18/1/2017 00:00:00", "25/1/2017 00:00:00")
            private val END_DAYS = listOf("10/1/2017 00:00:00", "17/1/2017 00:00:00", "24/1/2017 00:00:00", "31/1/2017 00:00:00")
            private val WEEK_DAYS = listOf(RealmList<WeekDaysRealm>(), RealmList<WeekDaysRealm>(),
                    RealmList<WeekDaysRealm>(), RealmList<WeekDaysRealm>())

            fun buildValidCalendarDataRealm() =
                    TestBuilder().instId("1").userId("1").week("1")
                            .year("2017")
                            .startDate("4/1/2017 00:00:00")
                            .startDay("4/1/2017 00:00:00")
                            .endDay("4/1/2017 00:00:00")
                            .weekDayResponsRealms(RealmList())
                            .build()

            fun buildList(): List<CalendarDataRealm> =
                    WEEKS.mapIndexed { index, week ->
                        TestBuilder().instId(INSTID[index]).userId(USERID[index]).year(YEARS[index]).week(week).startDate(START_DATES[index]).startDay(START_DAYS[index])
                                .endDay(END_DAYS[index]).weekDayResponsRealms(WEEK_DAYS[index])
                                .build()
                    }
        }

        private var instId: String? = ""
        private var userId: String? = ""
        private var week: String? = ""
        private var year: String? = ""
        private var currentDay: String? = ""
        private var startDate: String? = ""
        private var startDay: String? = ""
        private var endDay: String? = ""
        private var weekDayResponsRealms: RealmList<WeekDaysRealm>? = RealmList()

        fun instId(instId: String?): TestBuilder {
            this.instId = instId
            return this
        }

        fun userId(userId: String?): TestBuilder {
            this.userId = userId
            return this
        }

        fun week(week: String?): TestBuilder {
            this.week = week
            return this
        }

        fun year(year: String?): TestBuilder {
            this.year = year
            return this
        }

        fun currentDay(currentDay: String?): TestBuilder {
            this.currentDay = currentDay
            return this
        }

        fun startDate(startDate: String?): TestBuilder {
            this.startDate = startDate
            return this
        }

        fun startDay(startDay: String?): TestBuilder {
            this.startDay = startDay
            return this
        }

        fun endDay(endDay: String?): TestBuilder {
            this.endDay = endDay
            return this
        }

        fun weekDayResponsRealms(weekDayResponsRealms: RealmList<WeekDaysRealm>?): TestBuilder {
            this.weekDayResponsRealms = weekDayResponsRealms
            return this
        }

        override fun build(): CalendarDataRealm =
                CalendarDataRealm(instId ?: "", userId ?: "", week ?: "", year ?: "",
                        currentDay ?: "", startDate ?: "",
                        startDay ?: "", endDay ?: "", weekDayResponsRealms ?: RealmList())
    }

}
