package com.parent.entities

/**
 * Created by zMabrook on 03/04/18.
 */

data class Language(var id: Int = 0,
                    var displayName: String="",
                    var code: String="",
                    var direction: String="",
                    var flagPng: String="",
                    var flagSvg: String="",
                    var isSelected:Boolean = false)
