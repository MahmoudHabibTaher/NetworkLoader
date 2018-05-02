package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by mahmoud on 9/19/17.
 */
data class PermissionRemote(@SerializedName("id") var id: String = "",
                            @SerializedName("name") var name: String = "",
                            @SerializedName(value = "code", alternate = arrayOf("permission_code")) var code: String = "") {
    class Builder : IBuilder<PermissionRemote> {
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

        override fun build(): PermissionRemote =
                PermissionRemote(id, name, code)
    }

    class TestBuilder {
        companion object {
            private val idsList = listOf("1", "2")
            private val namesList = listOf("Permission1", "Permission2")
            private val codesList = listOf("Code 1", "Code 2")

            fun buildNormalPermission() =
                    Builder().id("1")
                            .name("Create role")
                            .code("create_role")
                            .build()

            fun buildList() =
                    idsList.mapIndexed { index, id ->
                        Builder().id(id).name(namesList[index]).code(codesList[index]).build()
                    }
        }
    }
}