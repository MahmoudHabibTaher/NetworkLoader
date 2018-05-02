package com.parent.entities


/**
 * Created by ahmedmahmoud on 3/4/18.
 */


class StatusData(val id: Int? = 0,
                 val name: String? = "",
                 val avatar: String? = "",
                 val children:List<UserRemote>?= listOf(),
                 val staff:List<UserRemote>?= listOf()) {

    class Builder : IBuilder<StatusData> {
        private var id: Int? = 0
        private var name: String? = ""
        private var avatar: String? = ""
        private var children: List<UserRemote>?= listOf()
        private var staff: List<UserRemote>?= listOf()


        fun id(id: Int?): Builder {
            this.id = id
            return this
        }

        fun name(name: String?): Builder {
            this.name = name
            return this
        }

        fun avatar(avatar: String?): Builder {
            this.avatar = avatar
            return this
        }

        fun children(children: List<UserRemote>?): Builder {
            this.children = children
            return this
        }
        fun staff(staff: List<UserRemote>?):Builder{
            this.staff=staff
            return this
        }

        override fun build(): StatusData =
                StatusData(id, name, avatar,children,staff)
    }
    class TestBuilder {
        companion object {
            fun buildValidStatusDataRemote() =
                    Builder().id(0)
                            .name("Checked In")
                            .avatar("https://s3.eu-central-1.amazonaws.com/parent-backend-testing-assets/seed-images/children/7.jpg").children(listOf()).staff(listOf())
                            .build()

            fun buildEmptyStatusRemote() =
                    Builder().id(0)
                            .name("0")
                            .avatar("").children(listOf()).staff(listOf())
                            .build()
        }
    }
}