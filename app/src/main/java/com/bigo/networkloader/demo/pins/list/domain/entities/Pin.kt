package com.bigo.networkloader.demo.pins.list.domain.entities

data class Pin(
    val id: String,
    val user: User,
    val urls: PinUrls
) {
    class Builder {
        private var id = ""
        private var user = User.Builder().build()
        private var urls = PinUrls.Builder().build()

        fun id(id: String) = apply {
            this.id = id
        }

        fun user(user: User) = apply {
            this.user = user
        }

        fun urls(urls: PinUrls) = apply {
            this.urls = urls
        }

        fun build(): Pin =
            Pin(id, user, urls)
    }
}