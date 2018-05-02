package com.parent.entities

data class ClassDetails(val classModel: ClassModel,
                        val currentChildren: List<ClassChild>,
                        val staff: List<ClassStaff>,
                        val futureChildren: List<ClassChild>,
                        val totalCurrentChildren: Int,
                        val totalStaff: Int,
                        val totalFutureChildren: Int) {
    class Builder : IBuilder<ClassDetails> {
        private var clazz: ClassModel = ClassModel()
        private var currentChildren = listOf<ClassChild>()
        private var staff = listOf<ClassStaff>()
        private var futureChildren = listOf<ClassChild>()
        private var totalCurrentChildren = 0
        private var totalStaff = 0
        private var totalFutureChildren = 0

        fun clazz(clazz: ClassModel): Builder {
            this.clazz = clazz
            return this
        }

        fun currentChildren(children: List<ClassChild>): Builder {
            this.currentChildren = children
            return this
        }

        fun staff(staff: List<ClassStaff>): Builder {
            this.staff = staff
            return this
        }

        fun futureChildren(children: List<ClassChild>): Builder {
            this.futureChildren = children
            return this
        }

        fun totalCurrentChildren(totalCurrentChildren: Int): Builder {
            this.totalCurrentChildren = totalCurrentChildren
            return this
        }

        fun totalStaff(totalStaff: Int): Builder {
            this.totalStaff = totalStaff
            return this
        }

        fun totalFutureChildren(totalFutureChildren: Int): Builder {
            this.totalFutureChildren = totalFutureChildren
            return this
        }

        override fun build(): ClassDetails =
                ClassDetails(clazz, currentChildren, staff, futureChildren,
                        totalCurrentChildren, totalStaff, totalFutureChildren)

        fun clone(classDetails: ClassDetails) =
                classDetails.let {
                    clazz(it.classModel).currentChildren(it.currentChildren)
                            .staff(it.staff)
                            .futureChildren(it.futureChildren)
                            .totalCurrentChildren(it.totalCurrentChildren)
                            .totalFutureChildren(it.totalFutureChildren)
                            .totalStaff(it.totalStaff)
                }
    }

    class TestBuilder {
        companion object {
            fun buildClassDetails() =
                    Builder().clazz(ClassModel.TestBuilder.buildNormalClass())
                            .currentChildren(ClassChild.TestBuilder.buildList())
                            .staff(ClassStaff.TestBuilder.buildList())
                            .futureChildren(ClassChild.TestBuilder.buildList())
                            .totalCurrentChildren(12)
                            .totalStaff(15)
                            .totalFutureChildren(3)
                            .build()
        }
    }
}