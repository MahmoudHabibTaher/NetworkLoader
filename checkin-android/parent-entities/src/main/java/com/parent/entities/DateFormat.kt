package com.parent.entities

/**
 * Created by mahmoud on 12/5/17.
 */
data class DateFormat(val name: String = "",
                      val format: String = "") {

    override fun toString(): String =
            name

    class Builder : IBuilder<DateFormat> {
        private var name = ""
        private var format = ""

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun format(format: String): Builder {
            this.format = format
            return this
        }

        override fun build(): DateFormat =
                DateFormat(name, format)
    }

    class TestBuilder {
        companion object {
            fun buildTimeFormat() =
                    Builder().name("24 hours format")
                            .format("HH")
                            .build()
        }
    }
}