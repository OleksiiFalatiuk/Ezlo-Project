package com.example.ezloproject.ui

import androidx.annotation.DrawableRes
import com.example.ezloproject.Constants
import com.example.ezloproject.R

class IconMapper {

    private val VERA_PLUS = R.drawable.vera_plus_big
    private val VERA_SECURE = R.drawable.vera_secure_big
    private val VERA_EDGE = R.drawable.vera_edge_big

    private val icons = mapOf(
        Constants.SERCOMM_G450_MODEL to VERA_PLUS,
        Constants.SERCOMM_G550_MODEL to VERA_SECURE,
        Constants.MICASAVERDE_MODEL to VERA_EDGE,
        Constants.SERCOMM_NA900_MODEL to VERA_EDGE,
        Constants.SERCOMM_NA301_MODEL to VERA_EDGE,
        Constants.SERCOMM_NA930_MODEL to VERA_EDGE
    )

    @DrawableRes
    fun getIcon(platform: String): Int = icons[platform] ?: VERA_PLUS

}