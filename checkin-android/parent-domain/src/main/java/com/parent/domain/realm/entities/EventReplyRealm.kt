package com.parent.domain.realm.entities

import com.parent.entities.IBuilder
import io.realm.RealmObject

/**
 * Created by mahmoud on 11/27/17.
 */
open class EventReplyRealm(var id: String = "",
                           var name: String = "",
                           var attending: Boolean = false,
                           var note: String = "",
                           var extraChildrenCount: Int = 0,
                           var extraAdultsCount: Int = 0) : RealmObject() {
    class Builder : IBuilder<EventReplyRealm> {
        private var id = ""
        private var name = ""
        private var attending = false
        private var note = ""
        private var extraChildrenCount = 0
        private var extraAdultsCount = 0

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun attending(attending: Boolean): Builder {
            this.attending = attending
            return this
        }

        fun note(note: String): Builder {
            this.note = note
            return this
        }

        fun extraChildrenCount(extraChildrenCount: Int): Builder {
            this.extraChildrenCount = extraChildrenCount
            return this
        }

        fun extraAdultsCount(extraAdultsCount: Int): Builder {
            this.extraAdultsCount = extraAdultsCount
            return this
        }

        override fun build(): EventReplyRealm =
                EventReplyRealm(id, name, attending, note, extraChildrenCount, extraAdultsCount)
    }
}