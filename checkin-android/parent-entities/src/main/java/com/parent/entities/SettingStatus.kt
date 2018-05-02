package com.parent.entities

data class SettingStatus(val name: String = "",
                         val key: String = "",
                         val relatedKey: String = "",
                         var value: Boolean = false,
                         val hint: String = "") {
    class Builder : IBuilder<SettingStatus> {
        private var name = ""
        private var key = ""
        private var relatedKey = ""
        private var value = false
        private var hint = ""

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun key(key: String): Builder {
            this.key = key
            return this
        }

        fun relatedKey(relatedKey: String): Builder {
            this.relatedKey = relatedKey
            return this
        }

        fun value(value: Boolean): Builder {
            this.value = value
            return this
        }

        fun hint(hint: String): Builder {
            this.hint = hint
            return this
        }

        override fun build(): SettingStatus =
                SettingStatus(name, key, relatedKey, value, hint)
    }
}