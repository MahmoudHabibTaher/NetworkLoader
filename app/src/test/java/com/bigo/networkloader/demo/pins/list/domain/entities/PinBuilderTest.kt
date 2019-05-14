package com.bigo.networkloader.demo.pins.list.domain.entities

import junit.framework.Assert.assertEquals
import org.junit.Test

class PinBuilderTest {
    @Test
    fun testBuilder() {
        val id = "1"
        val user = buildUser(
            "2", "JohnDoe", "John Doe", buildProfileImage("small", "medium", "large")
        )
        val urls = buildUrls("raw", "full", "regular", "small", "thumb")

        val pin = Pin.Builder()
            .id(id)
            .user(user)
            .urls(urls)
            .build()

        pin.apply {
            assertEquals(id, this.id)
            assertEquals(user, this.user)
            assertEquals(urls, this.urls)
        }

        val id2 = "2"
        val user2 = buildUser(
            "3", "JohnDoe", "John Doe", buildProfileImage("small", "medium", "large")
        )
        val urls2 = buildUrls("raw2", "full2", "regular2", "small2", "thumb2")

        val pin2 = Pin.Builder()
            .id(id2)
            .user(user2)
            .urls(urls2)
            .build()

        pin2.apply {
            assertEquals(id2, this.id)
            assertEquals(user2, this.user)
            assertEquals(urls2, this.urls)
        }
    }

    private fun buildUser(id: String, userName: String, name: String, image: ProfileImage) =
        User.Builder()
            .id(id)
            .userName(userName)
            .name(name)
            .image(image)
            .build()

    private fun buildProfileImage(small: String, medium: String, large: String) =
        ProfileImage.Builder()
            .small(small)
            .medium(medium)
            .large(large)
            .build()

    private fun buildUrls(raw: String, full: String, regular: String, small: String, thumb: String) =
        PinUrls.Builder()
            .raw(raw)
            .full(full)
            .regular(regular)
            .small(small)
            .thumb(thumb)
            .build()

}