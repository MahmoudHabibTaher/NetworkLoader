package com.bigo.networkloader.demo.pins.list.domain.entities

import org.junit.Assert.*
import org.junit.Test

class ProfileImageBuilderTest {
    @Test
    fun testBuilder() {
        val small = "smallUrl"
        val medium = "mediumUrl"
        val large = "largeUrl"

        val profileImage = ProfileImage.Builder()
            .small(small)
            .medium(medium)
            .large(large)
            .build()

        profileImage.apply {
            assertEquals(small, this.small)
            assertEquals(medium, this.medium)
            assertEquals(large, this.large)
        }

        val small2 = "anotherSmallUrl"
        val medium2 = "anotherMediumUrl"
        val large2 = "anotherLargeUrl"

        val profileImage2 = ProfileImage.Builder()
            .small(small2)
            .medium(medium2)
            .large(large2)
            .build()

        profileImage2.apply {
            assertEquals(small2, this.small)
            assertEquals(medium2, this.medium)
            assertEquals(large2, this.large)
        }
    }
}