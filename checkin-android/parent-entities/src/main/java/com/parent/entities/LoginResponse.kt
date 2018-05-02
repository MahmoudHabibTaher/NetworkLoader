package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by mahmoud on 9/8/17.
 */
class LoginResponse : BaseResponse<LoginData>()

data class LoginData(
        @SerializedName("token") var token: String? = "",
        @SerializedName("refresh_token") var refreshToken: String? = "",
        @SerializedName("user") var user: UserRemote) {
    class Builder : IBuilder<LoginData> {
        private var token = ""
        private var refreshToken = ""
        private var user = UserRemote()

        fun token(token: String): Builder {
            this.token = token
            return this
        }

        fun refreshToken(refreshToken: String): Builder {
            this.refreshToken = refreshToken
            return this
        }

        fun user(user: UserRemote): Builder {
            this.user = user
            return this
        }

        override fun build(): LoginData =
                LoginData(token, refreshToken, user)
    }

    class TestBuilder {
        companion object {
            fun buildSuccessLoginData() =
                    Builder().token("accessToken")
                            .refreshToken("refreshToken")
                            .user(UserRemote.TestBuilder.buildNormalUser())
                            .build()
        }
    }
}