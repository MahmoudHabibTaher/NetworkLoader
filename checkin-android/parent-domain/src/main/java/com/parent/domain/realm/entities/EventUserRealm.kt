package com.parent.domain.realm.entities

import com.parent.entities.IBuilder
import io.realm.RealmObject

/**
 * Created by mahmoud on 11/27/17.
 */
open class EventUserRealm(var id: String = "",
                          var name: String = "",
                          var avatar: String = "",
                          var className: String = "") : RealmObject() {
    class Builder : IBuilder<EventUserRealm> {
        private var id = ""
        private var name = ""
        private var avatar = ""
        private var className = ""

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun avatar(avatar: String): Builder {
            this.avatar = avatar
            return this
        }

        fun className(className: String): Builder {
            this.className = className
            return this
        }

        override fun build(): EventUserRealm =
                EventUserRealm(id, name, avatar, className)
    }
}