package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by mahmoud on 12/22/17.
 */
data class LeaveRemote(@SerializedName("type") val type: String? = "",
                       @SerializedName("value") val value: Int? = 0) {
    object Type {
        const val VACATION = "child_vacation"
        const val SICK = "child_sick"
    }

    class Builder : IBuilder<LeaveRemote> {
        private var type = ""
        private var value = 0

        fun type(type: String): Builder {
            this.type = type
            return this
        }

        fun value(value: Int): Builder {
            this.value = value
            return this
        }

        override fun build(): LeaveRemote =
                LeaveRemote(type, value)
    }

    class TestBuilder {
        companion object {
            fun buildLeave(type: String) =
                    Builder().type(type).value(10).build()

            fun buildList() =
                    listOf(buildLeave(Type.SICK))
        }
    }
}