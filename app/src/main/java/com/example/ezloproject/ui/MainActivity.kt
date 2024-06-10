package com.example.ezloproject.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ezloproject.Constants
import com.example.ezloproject.navigation.Navigation
import com.example.ezloproject.ui.details.compose.ItemDetailsScreen
import com.example.ezloproject.ui.main.compose.MainScreen

class MainActivity : AppCompatActivity() {

    private var splashScreenActive = true

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { splashScreenActive }
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Navigation.Main.route,
            ) {

                composable(Navigation.Main.route) {
                    MainScreen(
                        onLoadData = { splashScreenActive = false },
                        onItemClick = { id, isEditMode ->
                            navController.navigate(Navigation.ItemDetails.createRoute(id, isEditMode))
                        }
                    )
                }
                composable(
                    route = Navigation.ItemDetails.route,
                    arguments = listOf(
                        navArgument(Constants.ITEM_ID_ARGUMENT_KEY) { type = NavType.IntType },
                        navArgument(Constants.IS_EDIT_MODE_ARGUMENT_KEY) { type = NavType.BoolType }
                    )
                ) { backStackEntry ->
                    val itemId = backStackEntry.arguments?.getInt(Constants.ITEM_ID_ARGUMENT_KEY) ?: 0
                    val isEditMode = backStackEntry.arguments?.getBoolean(Constants.IS_EDIT_MODE_ARGUMENT_KEY) ?: false
                    ItemDetailsScreen(itemId = itemId, isEditMode = isEditMode, navController = navController)
                }
            }
        }
    }
}