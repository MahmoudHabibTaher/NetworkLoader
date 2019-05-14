package com.bigo.networkloader.demo.pins.list.domain.entities

import org.junit.Assert.*
import org.junit.Test

class UserBuilderTest {
    @Test
    fun testBuilder() {
        val id = "1"
        val userName = "johndoe"
        val name = "John Doe"
        val profileImage = buildProfileImage("small", "medium", "large")

        val user = User.Builder()
            .id(id)
            .userName(userName)
            .name(name)
            .image(profileImage)
            .build()

        user.apply {
            assertEquals(id, this.id)
            assertEquals(userName, this.userName)
            assertEquals(name, this.name)
            assertEquals(profileImage, this.image)
        }

        val id2 = "2"
        val userName2 = "janedoe"
        val name2 = "Jane Doe"
        val profileImage2 = buildProfileImage("small2", "medium2", "large2")

        val user2 = User.Builder()
            .id(id2)
            .userName(userName2)
            .name(name2)
            .image(profileImage2)
            .build()

        user2.apply {
            assertEquals(id2, this.id)
            assertEquals(userName2, this.userName)
            assertEquals(name2, this.name)
            assertEquals(profileImage2, this.image)
        }
    }

    private fun buildProfileImage(small: String, medium: String, large: String) =
        ProfileImage.Builder()
            .small(small)
            .medium(medium)
            .large(large)
            .build()
}