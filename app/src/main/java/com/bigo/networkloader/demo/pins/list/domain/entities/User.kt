package com.bigo.networkloader.demo.pins.list.domain.entities

data class User(
    val id: String,
    val userName: String,
    val name: String,
    val image: ProfileImage
) {
    class Builder {
        private var id = ""
        private var userName = ""
        private var name = ""
        private var image = ProfileImage.Builder().build()

        fun id(id: String) = apply {
            this.id = id
        }

        fun userName(userName: String) = apply {
            this.userName = userName
        }

        fun name(name: String) = apply {
            this.name = name
        }

        fun image(image: ProfileImage) = apply {
            this.image = image
        }

        fun build(): User =
            User(id, userName, name, image)
    }
}