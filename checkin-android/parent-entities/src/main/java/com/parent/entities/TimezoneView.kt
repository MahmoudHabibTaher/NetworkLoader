package com.parent.entities

/**
 * Created by mahmoud on 11/28/17.
 */
data class TimezoneView(val key: String = "",
                        val value: String = "") {
    override fun toString(): String =
            value

    class Builder : IBuilder<TimezoneView> {
        private var key = ""
        private var value = ""

        fun key(key: String): Builder {
            this.key = key
            return this
        }

        fun value(value: String): Builder {
            this.value = value
            return this
        }

        override fun build(): TimezoneView =
                TimezoneView(key, value)
    }

    class TestBuilder {
        companion object {
            fun buildNormalTimezone() =
                    Builder().key("UTC").value("(UTC -00:00) UTC")
                            .build()

            fun buildList() =
                    listOf(buildNormalTimezone())
        }
    }
}