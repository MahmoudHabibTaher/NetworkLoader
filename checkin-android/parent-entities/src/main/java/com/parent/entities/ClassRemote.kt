package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by mahmoud on 10/17/17.
 */
class ClassRemote(@SerializedName("id") val id: String? = "",
                  @SerializedName("institution_id") val instituteId: String? = "",
                  @SerializedName("name") val name: String? = "",
                  @SerializedName("avatar") val avatar: String? = "",
                  @SerializedName("description") val description: String? = "",
                  @SerializedName("staff_ratio") val staffRatio: String? = "",
                  @SerializedName("capacity") val capacity: Int? = 0,
                  @SerializedName("phone") val phone: String? = "",
                  @SerializedName("total_children") val totalChildren: Int? = 0,
                  @SerializedName("total_children_check_in") val totalChildrenCheckedIn: Int? = 0,
                  @SerializedName("status") val status: List<StatusRemote>? = listOf(),
                  @SerializedName("settings") val settings: ClassSettingsRemote? = null) {

    class Builder : IBuilder<ClassRemote> {
        private var id: String? = ""
        private var instituteId: String? = ""
        private var name: String? = ""
        private var avatar: String? = ""
        private var description: String? = ""
        private var staffRatio: String? = ""
        private var capacity: Int? = 0
        private var phone: String? = ""
        private var totalChildren: Int? = 0
        private var totalChildrenCheckedIn: Int? = 0
        private var status: List<StatusRemote>? = listOf()
        private var settings: ClassSettingsRemote? = ClassSettingsRemote()

        fun id(id: String?): Builder {
            this.id = id
            return this
        }

        fun instituteId(instituteId: String?): Builder {
            this.instituteId = instituteId
            return this
        }

        fun name(name: String?): Builder {
            this.name = name
            return this
        }

        fun avatar(avatar: String?): Builder {
            this.avatar = avatar
            return this
        }

        fun description(description: String?): Builder {
            this.description = description
            return this
        }

        fun staffRatio(staffRatio: String?): Builder {
            this.staffRatio = staffRatio
            return this
        }

        fun capacity(capacity: Int?): Builder {
            this.capacity = capacity
            return this
        }

        fun phone(phone: String?): Builder {
            this.phone = phone
            return this
        }

        fun totalChildren(totalChildren: Int?): Builder {
            this.totalChildren = totalChildren
            return this
        }

        fun totalChildrenCheckedIn(totalChildrenCheckedIn: Int?): Builder {
            this.totalChildrenCheckedIn = totalChildrenCheckedIn
            return this
        }

        fun status(status: List<StatusRemote>?): Builder {
            this.status = status
            return this
        }

        fun settings(settings: ClassSettingsRemote?): Builder {
            this.settings = settings
            return this
        }

        override fun build(): ClassRemote =
                ClassRemote(id, instituteId, name, avatar, description, staffRatio, capacity,
                        phone, totalChildren, totalChildrenCheckedIn, status, settings)
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