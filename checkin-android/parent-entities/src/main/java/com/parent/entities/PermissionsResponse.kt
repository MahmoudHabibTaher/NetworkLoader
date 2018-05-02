package com.parent.entities

/**
 * Created by mahmoud on 9/19/17.
 */
class PermissionsResponse(status: String? = null,
                          message: String? = null,
                          data: List<PermissionGroupRemote>? = null) : BaseResponse<List<PermissionGroupRemote>>(status, message, data) {
    class Builder : IBuilder<PermissionsResponse> {
        private var message: String? = ""
        private var status: String? = ""
        private var data: List<PermissionGroupRemote>? = listOf()

        fun message(message: String?): Builder {
            this.message = message
            return this
        }

        fun status(status: String?): Builder {
            this.status = status
            return this
        }

        fun data(data: List<PermissionGroupRemote>?): Builder {
            this.data = data
            return this
        }

        override fun build(): PermissionsResponse =
                PermissionsResponse(status, message, data)
    }

    class TestBuilder {
        companion object {
            fun buildSuccessResponse() =
                    Builder().status("Success").message("Success")
                            .data(PermissionGroupRemote.TestBuilder.buildList())
                            .build()


            fun buildEmptyResponse() =
                    Builder().status("Success").message("Success")
                            .data(null)
                            .build()
        }

    }
}