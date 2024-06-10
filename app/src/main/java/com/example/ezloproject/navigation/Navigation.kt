package com.example.ezloproject.navigation

import com.example.ezloproject.Constants

sealed class Navigation(val route: String) {
    data object Main : Navigation(Constants.MAIN_NAVIGATION_FRAGMENT)
    data object ItemDetails : Navigation(Constants.ITEM_DETAILS_NAVIGATION_FRAGMENT) {
        fun createRoute(itemId: Int, isEditMode: Boolean) = "itemDetails/$itemId/$isEditMode"
    }
}