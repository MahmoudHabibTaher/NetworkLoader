package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by zMabrook on 03/04/18.
 */

data class LanguageRemote(@SerializedName("id") var id: Int = 0,
                     @SerializedName("display_name") var displayName: String = "",
                     @SerializedName("code") var code: String = "",
                     @SerializedName("direction") var direction: String = "",
                     @SerializedName("flag_png") var flagPng: String = "",
                     @SerializedName("flag_svg") var flagSvg: String = "") {

    class TestBuilder : IBuilder<Language> {
        companion object {

            fun buildValidLanguage()=
                    TestBuilder().id(1)
                            .displayName("English")
                            .code("en")
                            .direction("LTR")
                            .build()
        }

        private var id: Int = 0
        private var displayName: String = ""
        private var code: String = ""
        private var direction: String = ""
        private var flagPng: String = ""
        private var flagSvg: String = ""

        fun id(id: Int): TestBuilder {
            this.id = id
            return this
        }

        fun displayName(name: String): TestBuilder {
            this.displayName = name
            return this
        }

        fun code(code: String): TestBuilder {
            this.code = code
            return this
        }

        fun direction(direction: String): TestBuilder {
            this.direction = direction
            return this
        }

        fun flagPng(flagPng: String): TestBuilder {
            this.flagPng = flagPng
            return this
        }

        fun flagSvg(flagSvg: String): TestBuilder {
            this.flagSvg = flagSvg
            return this
        }



        override fun build(): Language =
                Language(id, displayName, code, direction, flagPng,flagSvg)
    }
}
