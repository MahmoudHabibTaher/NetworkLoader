package com.parent.entities


class InstituteMeal(
      val id: Int = 0,
      val name: String? = "") {
    class Builder : IBuilder<InstituteMeal> {
        private var id = 0
        private var name = ""


        fun id(id: Int): Builder {
            this.id = id
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }


        override fun build(): InstituteMeal =
                InstituteMeal(id, name)

    }

    class TestBuilder {
        companion object {
            fun buildInstituteMeal() =
                    Builder().id(2).name("Name")
                            .build()
        }
    }

}