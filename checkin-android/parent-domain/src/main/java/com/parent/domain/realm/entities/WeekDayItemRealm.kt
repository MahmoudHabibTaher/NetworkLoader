package com.parent.domain.realm.entities

import com.parent.entities.IBuilder
import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by raede on 26/10/2017.
 */
open class WeekDayItemRealm(
        var id: String = "",
        var contentType: String = "",
        var contentId: String = "",
        var title: String = "",
        var date: String = "",
        var startTime: String = "",
        var endTime: String = "",
        var remainingRecipients: String = "",
        var recipients: RealmList<RecipientsRealm> = RealmList()) : RealmObject() {

    class TestBuilder : IBuilder<WeekDayItemRealm> {
        companion object {
            private val IDS = listOf("1", "2", "3", "4")
            private val CONTENT_TYPES = listOf("event", "activity", "holiday", "event")
            private val CONTENT_IDS = listOf("1", "2", "3", "4")
            private val TITLES = listOf("TITLE 1", "TITLE 2", "TITLE 3", "TITLE 4")
            private val DATES = listOf("9/1/2017 00:00:00", "10/1/2017 00:00:00", "11/1/2017 00:00:00", "12/1/2017 00:00:00")
            private val START_TIMES = listOf("9/1/2017 00:00:00", "10/1/2017 00:00:00", "11/1/2017 00:00:00", "12/1/2017 00:00:00")
            private val END_TIMES = listOf("10/1/2017 00:00:00", "11/1/2017 00:00:00", "12/1/2017 00:00:00", "13/1/2017 00:00:00")
            private val REMAINING_RECIPIENTS = listOf("1", "2", "3", "4")
            private val RECIPIENTSLIST = listOf(RealmList<RecipientsRealm>(), RealmList<RecipientsRealm>(),
                    RealmList<RecipientsRealm>(), RealmList<RecipientsRealm>())


            fun buildValidWeekDayItemRealm() =
                    TestBuilder().id("1")
                            .contentType("4/1/2017 00:00:00")
                            .contentId("4/1/2017 00:00:00")
                            .title("4/1/2017 00:00:00")
                            .date("4/1/2017 00:00:00")
                            .startTime("4/1/2017 00:00:00")
                            .endTime("5/1/2017 00:00:00")
                            .remainingRecipients("1")
                            .recipients(RealmList<RecipientsRealm>())
                            .build()

            fun buildList(): List<WeekDayItemRealm> =
                    IDS.mapIndexed { index, id ->
                        TestBuilder().id(id).contentType(CONTENT_TYPES[index]).contentId(CONTENT_IDS[index])
                                .title(TITLES[index]).date(DATES[index]).startTime(START_TIMES[index]).endTime(END_TIMES[index])
                                .remainingRecipients(REMAINING_RECIPIENTS[index]).recipients(RECIPIENTSLIST[index])
                                .build()
                    }
        }

        private var id: String? = ""
        private var contentType: String? = ""
        private var contentId: String? = ""
        private var title: String? = ""
        private var date: String? = ""
        private var startTime: String? = ""
        private var endTime: String? = ""
        private var remainingRecipients: String? = ""
        private var recipients: RealmList<RecipientsRealm>? = RealmList()


        fun id(id: String?): TestBuilder {
            this.id = id
            return this
        }

        fun contentType(contentType: String?): TestBuilder {
            this.contentType = contentType
            return this
        }

        fun contentId(contentId: String?): TestBuilder {
            this.contentId = contentId
            return this
        }

        fun title(title: String?): TestBuilder {
            this.title = title
            return this
        }

        fun date(date: String?): TestBuilder {
            this.date = date
            return this
        }

        fun startTime(startTime: String?): TestBuilder {
            this.startTime = startTime
            return this
        }

        fun endTime(endTime: String?): TestBuilder {
            this.endTime = endTime
            return this
        }

        fun remainingRecipients(remainingRecipients: String?): TestBuilder {
            this.remainingRecipients = remainingRecipients
            return this
        }

        fun recipients(recipients: RealmList<RecipientsRealm>?): TestBuilder {
            this.recipients = recipients
            return this
        }

        override fun build(): WeekDayItemRealm =
                WeekDayItemRealm(id ?: "", contentType ?: "", contentId ?: "",
                        title ?: "", date ?: "", startTime ?: "", endTime ?: "",
                        remainingRecipients ?: "", recipients ?: RealmList<RecipientsRealm>())
    }


}