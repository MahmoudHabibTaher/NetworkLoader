package com.parent.entities

/**
 * Created by mahmoud on 10/17/17.
 */
data class ClassModel(val id: String = "",
                      val instituteId: String = "",
                      val name: String = "",
                      val avatar: String = "",
                      val description: String = "",
                      val staffRatio: String = "",
                      val capacity: Int = 0,
                      val phone: String = "",
                      val totalChildren: Int = 0,
                      val totalChildrenCheckedIn: Int = 0,
                      val status: List<Status>? = listOf(),
                      val settings: ClassSettings = ClassSettings()) {

    class Builder : IBuilder<ClassModel> {
        private var id = ""
        private var instituteId = ""
        private var name = ""
        private var avatar = ""
        private var description = ""
        private var staffRatio = ""
        private var capacity = 0
        private var phone = ""
        private var totalChildren: Int = 0
        private var totalChildrenCheckedIn: Int = 0
        private var status: List<Status>? = listOf()
        private var settings = ClassSettings()

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun instituteId(id: String): Builder {
            this.instituteId = id
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

        fun description(description: String): Builder {
            this.description = description
            return this
        }

        fun staffRatio(staffRatio: String): Builder {
            this.staffRatio = staffRatio
            return this
        }

        fun capacity(capacity: Int): Builder {
            this.capacity = capacity
            return this
        }

        fun phone(phone: String): Builder {
            this.phone = phone
            return this
        }

        fun totalChildren(totalChildren: Int): Builder {
            this.totalChildren = totalChildren
            return this
        }

        fun totalChildrenCheckedIn(totalChildrenCheckedIn: Int): Builder {
            this.totalChildrenCheckedIn = totalChildrenCheckedIn
            return this
        }  fun status(status: List<Status>?): Builder {
            this.status = status
            return this
        }

        fun settings(settings: ClassSettings): Builder {
            this.settings = settings
            return this
        }

        override fun build(): ClassModel =
                ClassModel(id, instituteId, name, avatar, description, staffRatio, capacity, phone,
                        totalChildren, totalChildrenCheckedIn, status, settings)
    }

    class TestBuilder {
        companion object {
            fun buildNormalClass() =
                    Builder().id("1")
                            .name("Dolphins")
                            .avatar("avatar")
                            .totalChildren(40)
                            .totalChildrenCheckedIn(20)
                            .status(listOf())
                            .build()

            fun buildList() =
                    listOf(buildNormalClass())
        }
    }
}