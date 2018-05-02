package com.parent.entities

/**
 * Created by mahmoud on 12/22/17.
 */
data class Leave(val type: String = "", val value: Int = 0) {
    object Type {
        const val VACATION = "vacation"
        const val SICK = "sick"
    }

    class Builder : IBuilder<Leave> {
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

        override fun build(): Leave =
                Leave(type, value)
    }

    class TestBuilder {
        companion object {
            fun buildLeave(type: String) =
                    Builder().type(type).value(10).build()

            fun buildList() =
                    listOf(buildLeave(Type.VACATION))
        }
    }
}