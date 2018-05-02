package com.parent.entities

/**
 * Created by mahmoud on 12/23/17.
 */
data class LeaveView(var name: String = "", var type: String = "", var value: Int = 0) {
    class Builder : IBuilder<LeaveView> {
        private var name = ""
        private var type = ""
        private var value = 0

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun type(type: String): Builder {
            this.type = type
            return this
        }

        fun value(value: Int): Builder {
            this.value = value
            return this
        }

        override fun build(): LeaveView =
                LeaveView(name, type, value)
    }

    class TestBuilder {
        companion object {
            fun buildLeave() =
                    LeaveView("Sick", Leave.Type.SICK, 10)

            fun buildList() =
                    listOf(buildLeave())
        }
    }
}