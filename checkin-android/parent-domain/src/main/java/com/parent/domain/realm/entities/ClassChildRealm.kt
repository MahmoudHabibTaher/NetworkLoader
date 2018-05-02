package com.parent.domain.realm.entities

import com.parent.entities.IBuilder
import io.realm.RealmObject

/**
 * Created by mahmoud on 12/8/17.
 */
open class ClassChildRealm(var id: String = "",
                           var fullName: String = "",
                           var avatar: String = "",
                           var classId: String = "") : RealmObject() {
    class Builder : IBuilder<ClassChildRealm> {
        private var id = ""
        private var fullName = ""
        private var avatar = ""
        private var classId = ""

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun fullName(fullName: String): Builder {
            this.fullName = fullName
            return this
        }

        fun avatar(avatar: String): Builder {
            this.avatar = avatar
            return this
        }

        fun classId(classId: String): Builder {
            this.classId = classId
            return this
        }

        override fun build(): ClassChildRealm =
                ClassChildRealm(id, fullName, avatar, classId)
    }

    class TestBuilder {
        companion object {
            fun buildNormalClassChild() =
                    Builder().id("1")
                            .fullName("Mahmoud Habib")
                            .avatar("avatar")
                            .build()

            fun buildList() =
                    listOf(buildNormalClassChild())
        }
    }
}