package com.parent.domain.realm.entities

import com.parent.entities.IBuilder
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by mahmoud on 11/27/17.
 */
open class EventAdminRealm(@PrimaryKey var id: String = "",
                           var institutionId: String = "",
                           var title: String = "",
                           var description: String = "",
                           var recipients: RealmList<RecipientRealm> = RealmList(),
                           var date: String = "",
                           var startTime: String = "",
                           var endTime: String = "",
                           var rsvbRequired: Boolean = false,
                           var eventFor: String = "",
                           var replyDate: String = "",
                           var sendReminder: Boolean = false,
                           var photo: String? = "",
                           var totalInvitedAdults: Int = 0,
                           var totalInvitedChildren: Int = 0,
                           var remainingRecipients: Int = 0,
                           var adminReplies: RealmList<EventReplySectionRealm> = RealmList(),
                           var attendingChildrenCount: Int = 0,
                           var attendingStaffCount: Int = 0,
                           var attendingChildren: RealmList<EventUserRealm> = RealmList(),
                           var attendingStaff: RealmList<EventUserRealm> = RealmList(),
                           var notAttendingChildrenCount: Int = 0,
                           var notAttendingStaffCount: Int = 0,
                           var notAttendingChildren: RealmList<EventUserRealm> = RealmList(),
                           var notAttendingStaff: RealmList<EventUserRealm> = RealmList(),
                           var didNotReplyChildrenCount: Int = 0,
                           var didNotReplyStaffCount: Int = 0,
                           var didNotReplyChildren: RealmList<EventUserRealm> = RealmList(),
                           var didNotReplyStaff: RealmList<EventUserRealm> = RealmList(),
                           var canDelete: Boolean = false,
                           var daysUntilReply: Int = 0) : RealmObject() {
    class Builder : IBuilder<EventAdminRealm> {
        private var id = ""
        private var institutionId = ""
        private var title = ""
        private var description = ""
        private var recipients = RealmList<RecipientRealm>()
        private var date = ""
        private var startTime = ""
        private var endTime = ""
        private var rsvbRequired = false
        private var eventFor = ""
        private var replyDate = ""
        private var sendReminder = false
        private var photo: String? = ""
        private var remainingRecipients = 0
        private var totalInvitedChildren = 0
        private var totalInvitedAdults = 0
        private var adminReplies: RealmList<EventReplySectionRealm> = RealmList()
        private var attendingChildrenCount: Int = 0
        private var attendingStaffCount: Int = 0
        private var attendingChildren: RealmList<EventUserRealm> = RealmList()
        private var attendingStaff: RealmList<EventUserRealm> = RealmList()
        private var notAttendingChildrenCount: Int = 0
        private var notAttendingStaffCount: Int = 0
        private var notAttendingChildren: RealmList<EventUserRealm> = RealmList()
        private var notAttendingStaff: RealmList<EventUserRealm> = RealmList()
        private var didNotReplyChildrenCount: Int = 0
        private var didNotReplyStaffCount: Int = 0
        private var didNotReplyChildren: RealmList<EventUserRealm> = RealmList()
        private var didNotReplyStaff: RealmList<EventUserRealm> = RealmList()
        private var canDelete: Boolean = false
        private var daysUntilReply = 0

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

        fun date(date: String): Builder {
            this.date = date
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

        fun photo(photo: String?): Builder {
            this.photo = photo
            return this
        }

        fun remainingRecipients(remainingRecipients: Int): Builder {
            this.remainingRecipients = remainingRecipients
            return this
        }

        fun totalInvitedChildren(totalInvitedChildren: Int): Builder {
            this.totalInvitedChildren = totalInvitedChildren
            return this
        }

        fun totalInvitedAdults(totalInvitedAdults: Int): Builder {
            this.totalInvitedAdults = totalInvitedAdults
            return this
        }

        fun adminReplies(replies: RealmList<EventReplySectionRealm>): Builder {
            this.adminReplies = replies
            return this
        }

        fun attendingChildrenCount(attendingChildrenCount: Int): Builder {
            this.attendingChildrenCount = attendingChildrenCount
            return this
        }

        fun attendingStaffCount(attendingStaffCount: Int): Builder {
            this.attendingStaffCount = attendingStaffCount
            return this
        }

        fun attendingChildren(children: RealmList<EventUserRealm>): Builder {
            this.attendingChildren = children
            return this
        }

        fun attendingStaff(staff: RealmList<EventUserRealm>): Builder {
            this.attendingStaff = staff
            return this
        }

        fun notAttendingChildrenCount(notAttendingChildrenCount: Int): Builder {
            this.notAttendingChildrenCount = notAttendingChildrenCount
            return this
        }

        fun notAttendingStaffCount(notAttendingStaffCount: Int): Builder {
            this.notAttendingStaffCount = notAttendingStaffCount
            return this
        }

        fun notAttendingChildren(children: RealmList<EventUserRealm>): Builder {
            this.notAttendingChildren = children
            return this
        }

        fun notAttendingStaff(staff: RealmList<EventUserRealm>): Builder {
            this.notAttendingStaff = staff
            return this
        }

        fun didNotReplyChildrenCount(count: Int): Builder {
            this.didNotReplyChildrenCount = count
            return this
        }

        fun didNotReplyStaffCount(count: Int): Builder {
            this.didNotReplyStaffCount = count
            return this
        }

        fun didNotReplyChildren(children: RealmList<EventUserRealm>): Builder {
            this.didNotReplyChildren = children
            return this
        }

        fun didNotReplyStaff(staff: RealmList<EventUserRealm>): Builder {
            this.didNotReplyStaff = staff
            return this
        }

        fun canDelete(canDelete: Boolean): Builder {
            this.canDelete = canDelete
            return this
        }

        fun daysUntilReply(daysUntilReply: Int): Builder {
            this.daysUntilReply = daysUntilReply
            return this
        }

        override fun build(): EventAdminRealm =
                EventAdminRealm(id, institutionId, title, description, recipients, date, startTime, endTime, rsvbRequired,
                        eventFor, replyDate, sendReminder, photo,
                        remainingRecipients, totalInvitedChildren, totalInvitedAdults,
                        adminReplies,
                        attendingChildrenCount, attendingStaffCount,
                        attendingChildren, attendingStaff,
                        notAttendingChildrenCount, notAttendingStaffCount,
                        notAttendingChildren, notAttendingStaff,
                        didNotReplyChildrenCount, didNotReplyStaffCount,
                        didNotReplyChildren, didNotReplyStaff, canDelete, daysUntilReply)
    }
}