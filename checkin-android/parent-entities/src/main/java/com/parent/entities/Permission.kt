package com.parent.entities

/**
 * Created by mahmoud on 9/19/17.
 */
data class Permission(var id: String = "",
                      var name: String = "",
                      var code: String = "") {
    class Builder : IBuilder<Permission> {
        private var id: String = ""
        private var name: String = ""
        private var code: String = ""

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun code(code: String): Builder {
            this.code = code
            return this
        }

        override fun build(): Permission =
                Permission(id, name, code)
    }

    class TestBuilder {
        companion object {
            private val ids = listOf("1", "2")
            private val names = listOf("Permission 1", "Permission 2")
            private val codes = listOf("code 1", "code 2")

            fun buildNormalPermission() =
                    Builder().id("1").name("Permission 1").code("code 1").build()

            fun buildList(): List<Permission> =
                    ids.mapIndexed { index, id -> Builder().id(id).name(names[index]).code(codes[index]).build() }
        }
    }
}