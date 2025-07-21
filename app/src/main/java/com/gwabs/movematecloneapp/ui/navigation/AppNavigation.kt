package com.gwabs.movematecloneapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gwabs.movematecloneapp.ui.screens.CalculateShipmentScreen
import com.gwabs.movematecloneapp.ui.screens.EstimateResultScreen
import com.gwabs.movematecloneapp.ui.screens.HomeScreen
import com.gwabs.movematecloneapp.ui.screens.SearchScreen
import com.gwabs.movematecloneapp.ui.screens.ShipmentHistoryScreen


/*
   Sealed class for navigation routes.
 */
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Shipment : Screen("shipment")
    object Calculate : Screen("calculate")
    object Search : Screen("search")
    object EstimateResult : Screen("estimate_result/{amount}") {
        fun createRoute(amount: String) = "estimate_result/$amount"
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {

        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(Screen.Search.route) {
            SearchScreen(navController)
        }

        composable(Screen.Shipment.route) {
            ShipmentHistoryScreen(navController)
        }

        composable(Screen.Calculate.route) {
            CalculateShipmentScreen(navController)
        }

        composable(
            route = Screen.EstimateResult.route,
            arguments = listOf(navArgument("amount") { type = NavType.StringType })
        ) { backStackEntry ->
            val amount = backStackEntry.arguments?.getString("amount") ?: "0"
            EstimateResultScreen(amount, navController)
        }
    }
}

