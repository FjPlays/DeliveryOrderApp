package com.example.deliveryorderapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.deliveryorderapp.presentation.orderdetail.OrderDetailScreen
import com.example.deliveryorderapp.presentation.orderlist.OrderListScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.OrderList.route) {
        composable(Screen.OrderList.route) {
            OrderListScreen(
                onOrderClick = { orderId ->
                    navController.navigate(Screen.OrderDetail.createRoute(orderId))
                }
            )
        }
        composable(
            route = Screen.OrderDetail.route,
            arguments = listOf(navArgument("orderId") { type = NavType.StringType })
        ) { backStackEntry ->
            OrderDetailScreen(
                orderId = backStackEntry.arguments?.getString("orderId") ?: "",
                onBack = { navController.popBackStack() }
            )
        }
    }
}