package com.bigo.networkloader.demo.pins.list.domain.entities

data class ProfileImage(
    val small: String,
    val medium: String,
    val large: String
) {
    class Builder {
        private var small = ""
        private var medium = ""
        private var large = ""

        fun small(small: String) = apply {
            this.small = small
        }

        fun medium(medium: String) = apply {
            this.medium = medium
        }

        fun large(large: String) = apply {
            this.large = large
        }

        fun build(): ProfileImage =
            ProfileImage(small, medium, large)
    }
}