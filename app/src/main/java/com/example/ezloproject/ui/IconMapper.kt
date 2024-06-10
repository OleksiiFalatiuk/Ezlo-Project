package com.example.ezloproject.ui

import androidx.annotation.DrawableRes
import com.example.ezloproject.R

class IconMapper {

    private val VERA_PLUS = R.drawable.vera_plus_big
    private val VERA_SECURE = R.drawable.vera_secure_big
    private val VERA_EDGE = R.drawable.vera_edge_big

    private val icons = mapOf(
        "Sercomm G450" to VERA_PLUS,
        "Sercomm G550" to VERA_SECURE,
        "MiCasaVerde VeraLite" to VERA_EDGE,
        "Sercomm NA900" to VERA_EDGE,
        "Sercomm NA301" to VERA_EDGE,
        "Sercomm NA930" to VERA_EDGE
    )

    @DrawableRes
    fun getIcon(platform: String): Int = icons[platform] ?: VERA_PLUS

}