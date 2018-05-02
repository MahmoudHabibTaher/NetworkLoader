package com.parent.entities

/**
 * Created by habib on 3/25/18.
 */
data class ReportLeaveRequest(val children: List<String>,
                              val staff: List<String>,
                              val startDate: Long,
                              val endDate: Long,
                              val note: String,
                              val type: Type) {
    enum class Type(val type: String) {
        VACATION("vacation"),
        SICK("sick"),
        HAS_CHILD_SICK("has_child_sick"),
        UNKNOWN("")
    }

    class Builder : IBuilder<ReportLeaveRequest> {
        private var children = listOf<String>()
        private var staff = listOf<String>()
        private var startDate = -1L
        private var endDate = -1L
        private var note = ""
        private var type = Type.UNKNOWN

        fun children(children: List<String>): Builder {
            this.children = children
            return this
        }

        fun staff(staff: List<String>): Builder {
            this.staff = staff
            return this
        }

        fun startDate(startDate: Long): Builder {
            this.startDate = startDate
            return this
        }

        fun endDate(endDate: Long): Builder {
            this.endDate = endDate
            return this
        }

        fun note(note: String): Builder {
            this.note = note
            return this
        }

        fun type(type: Type): Builder {
            this.type = type
            return this
        }

        override fun build(): ReportLeaveRequest =
                ReportLeaveRequest(children, staff, startDate, endDate, note, type)
    }
}