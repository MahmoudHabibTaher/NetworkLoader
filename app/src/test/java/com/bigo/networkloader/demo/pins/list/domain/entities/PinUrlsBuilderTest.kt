package com.bigo.networkloader.demo.pins.list.domain.entities

import org.junit.Assert.*
import org.junit.Test

class PinUrlsBuilderTest {
    @Test
    fun testBuilder() {
        val raw = "raw"
        val full = "full"
        val regular = "regular"
        val small = "small"
        val thumb = "thumb"

        val pinUrls = PinUrls.Builder()
            .raw(raw)
            .full(full)
            .regular(regular)
            .small(small)
            .thumb(thumb)
            .build()

        pinUrls.apply {
            assertEquals(raw, this.raw)
            assertEquals(full, this.full)
            assertEquals(regular, this.regular)
            assertEquals(small, this.small)
            assertEquals(thumb, this.thumb)
        }

        val raw2 = "raw2"
        val full2 = "full2"
        val regular2 = "regular2"
        val small2 = "small2"
        val thumb2 = "thumb2"

        val pinUrls2 = PinUrls.Builder()
            .raw(raw2)
            .full(full2)
            .regular(regular2)
            .small(small2)
            .thumb(thumb2)
            .build()

        pinUrls2.apply {
            assertEquals(raw2, this.raw)
            assertEquals(full2, this.full)
            assertEquals(regular2, this.regular)
            assertEquals(small2, this.small)
            assertEquals(thumb2, this.thumb)
        }
    }
}