package com.parent.entities

import org.joda.time.DateTime

/**
 * Created by mahmoud on 12/30/17.
 */
data class PermissionReply(val message: String = "",
                           val replyBy: String = "",
                           val reply: Status = Status.NO_REPLY,
                           val date: Long = -1) {
    enum class Status {
        NO_REPLY, YES, NO
    }

    class Builder : IBuilder<PermissionReply> {
        private var message = ""
        private var replyBy = ""
        private var reply = Status.NO_REPLY
        private var date = -1L

        fun message(message: String): Builder {
            this.message = message
            return this
        }

        fun replyBy(replyBy: String): Builder {
            this.replyBy = replyBy
            return this
        }

        fun reply(reply: Status): Builder {
            this.reply = reply
            return this
        }

        fun date(date: Long): Builder {
            this.date = date
            return this
        }

        override fun build(): PermissionReply =
                PermissionReply(message, replyBy, reply, date)
    }

    class TestBuilder {
        companion object {
            fun buildReply(status: Status) =
                    Builder().message("Reply message")
                            .replyBy("Mahmoud Habib")
                            .reply(status)
                            .date(DateTime.now().millis)
                            .build()

            fun buildList() =
                    listOf(buildReply(Status.YES), buildReply(Status.NO), buildReply(Status.NO_REPLY))
        }
    }
}