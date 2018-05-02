package com.parent.domain.realm.entities

import com.parent.entities.IBuilder
import com.parent.domain.realm.base.toRealmList
import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by mahmoud on 12/31/17.
 */
open class ChildPermissionRealm(var id: String = "",
                                var childId: String = "",
                                var title: String = "",
                                var description: String = "",
                                var hasReply: Boolean = false,
                                var lastReply: String = "",
                                var replies: RealmList<PermissionReplyRealm> = RealmList()) : RealmObject() {
    class Builder : IBuilder<ChildPermissionRealm> {
        private var id = ""
        private var childId = ""
        private var title = ""
        private var description = ""
        private var hasReply = false
        private var lastReply = ""
        private var replies = RealmList<PermissionReplyRealm>()

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun childId(childId: String): Builder {
            this.childId = childId
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

        fun hasReply(hasReply: Boolean): Builder {
            this.hasReply = hasReply
            return this
        }

        fun lastReply(lastReply: String): Builder {
            this.lastReply = lastReply
            return this
        }

        fun replies(replies: RealmList<PermissionReplyRealm>): Builder {
            this.replies = replies
            return this
        }

        override fun build(): ChildPermissionRealm =
                ChildPermissionRealm(id, childId, title, description, hasReply, lastReply, replies)
    }

    class TestBuilder {
        companion object {
            fun buildChildPermissionWithNoReply() =
                    Builder().id("1").childId("2").title("Permission title")
                            .description("Child permission")
                            .lastReply("NO_REPLY")
                            .hasReply(false)
                            .replies(RealmList())
                            .build()

            fun buildChildPermissionWithReplyYes() =
                    Builder().id("1").childId("2").title("Permission title")
                            .description("Child permission")
                            .lastReply("YES")
                            .hasReply(true)
                            .replies(PermissionReplyRealm.TestBuilder.buildList().toRealmList())
                            .build()

            fun buildChildPermissionWithReplyNo() =
                    Builder().id("1").childId("2").title("Permission title")
                            .description("Child permission")
                            .lastReply("NO")
                            .hasReply(true)
                            .replies(PermissionReplyRealm.TestBuilder.buildList().toRealmList())
                            .build()
        }
    }
}