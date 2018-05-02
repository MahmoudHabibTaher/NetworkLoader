package com.parent.entities

/**
 * Created by mahmoud on 10/25/17.
 */
class RoleDetailsResponse(status: String? = "", message: String? = "", data: RoleRemote? = RoleRemote()) :
        BaseResponse<RoleRemote>(status, message, data) {
    class Builder : IBuilder<RoleDetailsResponse> {
        private var status: String? = ""
        private var message: String? = ""
        private var data: RoleRemote? = RoleRemote()

        fun status(status: String?): Builder {
            this.status = status
            return this
        }

        fun message(message: String?): Builder {
            this.message = message
            return this
        }

        fun data(data: RoleRemote?): Builder {
            this.data = data
            return this
        }

        override fun build(): RoleDetailsResponse =
                RoleDetailsResponse(status, message, data)
    }

    class TestBuilder {
        companion object {
            fun buildSuccessResponse() =
                    Builder().status("Success")
                            .message("Success")
                            .data(RoleRemote.TestBuilder.buildValidRemoteRole())
                            .build()

            fun buildSuccessResponseNoData() =
                    Builder().status("Success")
                            .message("Success")
                            .data(null)
                            .build()
        }
    }
}