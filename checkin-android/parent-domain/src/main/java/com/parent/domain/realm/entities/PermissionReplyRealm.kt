package com.parent.domain.realm.entities

import com.parent.domain.common.date.now
import com.parent.entities.IBuilder
import io.realm.RealmObject

/**
 * Created by mahmoud on 1/1/18.
 */
open class PermissionReplyRealm(var message: String = "",
                                var replyBy: String = "",
                                var reply: String = "",
                                var date: Long = -1) : RealmObject() {
    class Builder : IBuilder<PermissionReplyRealm> {
        private var message = ""
        private var replyBy = ""
        private var reply = ""
        private var date = -1L

        fun message(message: String): Builder {
            this.message = message
            return this
        }

        fun replyBy(replyBy: String): Builder {
            this.replyBy = replyBy
            return this
        }

        fun reply(reply: String): Builder {
            this.reply = reply
            return this
        }

        fun date(date: Long): Builder {
            this.date = date
            return this
        }

        override fun build(): PermissionReplyRealm =
                PermissionReplyRealm(message, replyBy, reply, date)
    }

    class TestBuilder {
        companion object {
            fun buildReplyWithReplyYes() =
                    Builder().message("Reply message")
                            .replyBy("Mahmoud Habib")
                            .reply("YES")
                            .date(now())
                            .build()

            fun buildReplyWithReplyNo() =
                    Builder().message("Reply message")
                            .replyBy("Mahmoud Habib")
                            .reply("NO")
                            .date(now())
                            .build()

            fun buildReplyWithNoReply() =
                    Builder().message("Reply message")
                            .replyBy("Mahmoud Habib")
                            .reply("NO_REPLY")
                            .date(now())
                            .build()

            fun buildList() =
                    listOf(buildReplyWithReplyYes(), buildReplyWithReplyNo(), buildReplyWithNoReply())

        }
    }
}