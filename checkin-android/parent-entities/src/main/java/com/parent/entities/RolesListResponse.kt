package com.parent.entities

/**
 * Created by mahmoud on 10/23/17.
 */
class RolesListResponse(status: String = "", message: String = "",
                        data: List<RoleRemoteSection> = listOf()) : BaseResponse<List<RoleRemoteSection>>(status, message, data) {
    class TestBuilder : IBuilder<RolesListResponse> {
        companion object {
            fun buildSuccessResponse() =
                    TestBuilder().status("Success").message("Success")
                            .data(RoleRemoteSection.TestBuilder.buildList())
                            .build()

            fun buildSuccessEmptyResponse() =
                    TestBuilder().status("Success").message("Success")
                            .data(emptyList())
                            .build()
        }

        private var status: String = ""
        private var message: String = ""
        private var data: List<RoleRemoteSection> = listOf()

        fun status(status: String): TestBuilder {
            this.status = status
            return this
        }

        fun message(message: String): TestBuilder {
            this.message = message
            return this
        }

        fun data(data: List<RoleRemoteSection>): TestBuilder {
            this.data = data
            return this
        }

        override fun build(): RolesListResponse = RolesListResponse(status, message, data)
    }
}