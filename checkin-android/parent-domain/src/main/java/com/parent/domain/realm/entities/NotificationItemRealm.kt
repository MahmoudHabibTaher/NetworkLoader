package com.parent.domain.realm.entities

import com.parent.entities.IBuilder
import io.realm.RealmObject

/**
 * Created by raede on 26/10/2017.
 */
open class NotificationItemRealm(

        var id: String? = "",
        var institutionId: String? = "",
        var photo: String? = "",
        var preText: String = "",
        var notificationSubject: String? = "",
        var notificationAction: String? = "",
        var entityName: String = "",
        var postText: String = "",
        var notificationDateTime: String? = "",
        var notificationDateTimeText: String? = "",
        var isRead: Boolean? = false,
        var contentType: String = "",
        var contentId: String = ""
) : RealmObject() {

    class TestBuilder : IBuilder<NotificationItemRealm> {
        companion object {
            private val IDS = listOf("1", "2", "3", "4")
            private val INSTITUTION_IDS = listOf("1", "2", "3", "4")
            private val PHOTOS = listOf("", "", "", "")
            private val SUBJECTS = listOf("SUBJECT 1", "SUBJECT 2", "SUBJECT 3", "SUBJECT 4")
            private val ACTIONS = listOf("ACTION 1", "ACTION 2", "ACTION 3", "ACTION 4")
            private val BODIES = listOf("BODY 1", "BODY 2", "BODY 3", "BODY 4")
            private val TIMES = listOf("9/1/2017 00:00:00", "10/1/2017 00:00:00", "11/1/2017 00:00:00", "12/1/2017 00:00:00")
            private val TIME_TEXTS = listOf("9/1/2017 00:00:00", "10/1/2017 00:00:00", "11/1/2017 00:00:00", "12/1/2017 00:00:00")
            private val ICONS = listOf("event.png", "event.png", "event.png", "event.png")
            private val IS_READ = listOf(false, false, false, false)
            private val CONTENT_TYPES = listOf("event", "activity", "holiday", "event")
            private val CONTENT_IDS = listOf("1", "2", "3", "4")

            fun buildValidNotificationItemModel() =
                    TestBuilder().id("1")
                            .institutionId("1")
                            .photo("")
                            .notificationSubject("SUBJECT 1")
                            .notificationAction("ACTION 1")
                            .notificationDateTime("9/1/2017 00:00:00")
                            .notificationDateTimeText("9/1/2017 00:00:00")
                            .isRead(false)
                            .contentId("event")
                            .contentId("1")
                            .build()

            fun buildList(): List<NotificationItemRealm> =
                    IDS.mapIndexed { index, id ->
                        TestBuilder().id(id).institutionId(INSTITUTION_IDS[index]).photo(PHOTOS[index]).notificationSubject(SUBJECTS[index])
                                .notificationAction(ACTIONS[index]).notificationDateTime(TIMES[index]).
                                notificationDateTimeText(TIME_TEXTS[index]).isRead(IS_READ[index]).
                                contentType(CONTENT_TYPES[index]).contentId(CONTENT_IDS[index])
                                .build()
                    }
        }

        private var id: String? = ""
        private var institutionId: String? = ""
        private var photo: String? = ""
        private var notificationSubject: String? = ""
        private var notificationAction: String? = ""
        private var notificationDateTime: String? = ""
        private var notificationDateTimeText: String? = ""
        private var isRead: Boolean? = false
        private var contentType: String? = ""
        private var contentId: String? = ""

        fun id(id: String?): TestBuilder {
            this.id = id
            return this
        }

        fun institutionId(institutionId: String?): TestBuilder {
            this.institutionId = institutionId
            return this
        }

        fun photo(photo: String?): TestBuilder {
            this.photo = photo
            return this
        }

        fun notificationSubject(notificationSubject: String?): TestBuilder {
            this.notificationSubject = notificationSubject
            return this
        }

        fun notificationAction(notificationAction: String?): TestBuilder {
            this.notificationAction = notificationAction
            return this
        }

        fun notificationDateTime(notificationDateTime: String?): TestBuilder {
            this.notificationDateTime = notificationDateTime
            return this
        }

        fun notificationDateTimeText(notificationDateTimeText: String?): TestBuilder {
            this.notificationDateTimeText = notificationDateTimeText
            return this
        }

        fun isRead(isRead: Boolean?): TestBuilder {
            this.isRead = isRead
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

        override fun build(): NotificationItemRealm =
                NotificationItemRealm(id ?: "", institutionId
                        ?: "", photo ?: "", "",
                        notificationSubject ?: "", notificationAction ?: "",
                        notificationDateTime ?: "", "", notificationDateTimeText ?: "",
                        "", isRead ?: false, contentType ?: "", contentId ?: "")
    }


}