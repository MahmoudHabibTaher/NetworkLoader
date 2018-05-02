package com.parent.entities

data class ClassSettings(val statuses: List<SettingStatus> = listOf()) {
    class Builder : IBuilder<ClassSettings> {
        private var statuses = listOf<SettingStatus>()

        fun statuses(statuses: List<SettingStatus>): Builder {
            this.statuses = statuses
            return this
        }

        override fun build(): ClassSettings =
                ClassSettings(statuses)
    }
}