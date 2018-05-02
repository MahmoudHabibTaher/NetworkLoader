package com.parent.domain.realm.entities

import com.parent.entities.IBuilder
import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by raede on 26/10/2017.
 */
open class WeekDaysRealm(var weekNumber: String = "",
                         var date: String = "",
                         var itemRespons: RealmList<WeekDayItemRealm> = RealmList()) : RealmObject() {

    class TestBuilder : IBuilder<WeekDaysRealm> {
        companion object {
            private val WEEKS = listOf("1", "2", "3", "4")
            private val DATES = listOf("9/1/2017 00:00:00", "10/1/2017 00:00:00", "11/1/2017 00:00:00", "12/1/2017 00:00:00")
            private val ITEMS = listOf(RealmList<WeekDayItemRealm>(), RealmList<WeekDayItemRealm>(),
                    RealmList<WeekDayItemRealm>(), RealmList<WeekDayItemRealm>())

            fun buildValidWeekDaysRealm() =
                    TestBuilder().weekNumber("1").date("9/1/2017 00:00:00").itemResponses(RealmList<WeekDayItemRealm>()).build()

        }

        private var weekNumber: String? = ""
        private var date: String? = ""
        private var itemResponses: RealmList<WeekDayItemRealm>? = RealmList()


        fun weekNumber(weekNumber: String?): TestBuilder {
            this.weekNumber = weekNumber
            return this
        }

        fun date(date: String?): TestBuilder {
            this.date = date
            return this
        }

        fun itemResponses(itemResponses: RealmList<WeekDayItemRealm>?): TestBuilder {
            this.itemResponses = itemResponses
            return this
        }

        override fun build(): WeekDaysRealm =
                WeekDaysRealm(weekNumber ?: "", date ?: "", itemResponses ?: RealmList())
    }

}