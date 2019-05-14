package com.bigo.networkloader.demo.pins.list.domain.entities

class PinUrls(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String
) {
    class Builder {
        private var raw = ""
        private var full = ""
        private var regular = ""
        private var small = ""
        private var thumb = ""

        fun raw(raw: String) = apply {
            this.raw = raw
        }

        fun full(full: String) = apply {
            this.full = full
        }

        fun regular(regular: String) = apply {
            this.regular = regular
        }

        fun small(small: String) = apply {
            this.small = small
        }

        fun thumb(thumb: String) = apply {
            this.thumb = thumb
        }

        fun build(): PinUrls =
            PinUrls(raw, full, regular, small, thumb)
    }
}