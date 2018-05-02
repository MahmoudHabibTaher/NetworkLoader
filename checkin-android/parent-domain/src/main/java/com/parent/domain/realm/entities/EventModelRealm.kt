package com.parent.domain.realm.entities

import com.parent.entities.IBuilder
import com.parent.entities.Event
import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by mahmoud on 11/23/17.
 */
open class EventModelRealm(var id: String = "",
                           var institutionId: String = "",
                           var title: String = "",
                           var description: String = "",
                           var recipients: RealmList<RecipientRealm> = RealmList(),
                           var remainingRecipients: String = "",
                           var date: String = "",
                           var dayOfWeek: String = "",
                           var day: String = "",
                           var month: String = "",
                           var startTime: String = "",
                           var endTime: String = "",
                           var rsvbRequired: Boolean = false,
                           var eventFor: String = "",
                           var replyDate: String = "",
                           var sendReminder: Boolean = false,
                           var totalInvitedChildren: String = "",
                           var totalInvitedAdults: String = "",
                           var photo: String = "",
                           var replied: String = "",
                           var canReply: Boolean = false,
                           var extra_children: String = "0",
                           var extra_adults: String = "0",
                           var replies: RealmList<AttendingChildrenModelRealm> = RealmList(),
                           var difference: Int = 0,
                           var userReplied: Boolean = false,
                           var recipientsCanReply: RealmList<RecipientRealm> = RealmList(),
                           var dateTimeText: String = "",
                           var canDelete: Boolean = false) : RealmObject() {
    class Builder : IBuilder<EventModelRealm> {
        private var id = ""
        private var institutionId = ""
        private var title = ""
        private var description = ""
        private var recipients = RealmList<RecipientRealm>()
        private var remainingRecipients: String = ""
        private var date = ""
        private var dayOfWeek: String = ""
        private var day = ""
        private var month = ""
        private var startTime = ""
        private var endTime = ""
        private var rsvbRequired = false
        private var eventFor = ""
        private var replyDate = ""
        private var sendReminder = false
        private var totalInvitedChildren: String = ""
        private var totalInvitedAdults: String = ""
        private var photo = ""

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun institutionId(institutionId: String): Builder {
            this.institutionId = institutionId
            return this
        }

        fun title(title: String): Builder {
            this.title = title
            return this
        }

        fun description(description: String): Builder {
            this.description = description
            return this
        }

        fun recipients(recipients: RealmList<RecipientRealm>): Builder {
            this.recipients = recipients
            return this
        }

        fun remainingRecipients(remainingRecipients: String): Builder {
            this.remainingRecipients = remainingRecipients
            return this
        }

        fun date(date: String): Builder {
            this.date = date
            return this
        }

        fun dayOfWeek(dayOfWeek: String): Builder {
            this.dayOfWeek = dayOfWeek
            return this
        }

        fun day(day: String): Builder {
            this.day = day
            return this
        }

        fun month(month: String): Builder {
            this.month = month
            return this
        }

        fun startTime(startTime: String): Builder {
            this.startTime = startTime
            return this
        }

        fun endTime(endTime: String): Builder {
            this.endTime = endTime
            return this
        }

        fun reservationRequired(rsvbRequired: Boolean): Builder {
            this.rsvbRequired = rsvbRequired
            return this
        }

        fun eventFor(eventFor: String): Builder {
            this.eventFor = eventFor
            return this
        }

        fun replyDate(replyDate: String): Builder {
            this.replyDate = replyDate
            return this
        }

        fun sendReminder(sendReminder: Boolean): Builder {
            this.sendReminder = sendReminder
            return this
        }

        fun totalInvitedChildren(totalInvitedChildren: String): Builder {
            this.totalInvitedChildren = totalInvitedChildren
            return this
        }

        fun totalInvitedAdults(totalInvitedAdults: String): Builder {
            this.totalInvitedAdults = totalInvitedAdults
            return this
        }

        fun photo(photo: String): Builder {
            this.photo = photo
            return this
        }

        override fun build(): EventModelRealm =
                EventModelRealm(id, institutionId, title, description, recipients, remainingRecipients, date, dayOfWeek, day, month, startTime, endTime, rsvbRequired,
                        eventFor, replyDate, sendReminder, photo)
    }

    class TestBuilder {
        companion object {
            fun buildNormalEvent() =
                    Builder().id("1")
                            .institutionId("1")
                            .title("Event Title")
                            .description("Event Description")
                            .remainingRecipients("2")
                            .date("12/12/2012")
                            .dayOfWeek("Monday")
                            .day("12")
                            .month("Dec")
                            .startTime("08:00 AM")
                            .endTime("08:00 PM")
                            .recipients(RealmList(RecipientRealm()))
                            .reservationRequired(true)
                            .eventFor(Event.EventFor.ADULTS_AND_CHILDREN)
                            .replyDate("11/12/2012")
                            .sendReminder(true)
                            .totalInvitedChildren("11")
                            .totalInvitedAdults("12")
                            .photo("event_photo")
                            .build()
        }
    }
}