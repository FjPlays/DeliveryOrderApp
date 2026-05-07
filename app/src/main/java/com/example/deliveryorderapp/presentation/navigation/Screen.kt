package com.example.deliveryorderapp.presentation.navigation

sealed class Screen(val route: String) {
    object OrderList   : Screen("order_list")
    object OrderDetail : Screen("order_detail/{orderId}") {
        fun createRoute(orderId: String) = "order_detail/$orderId"
    }
}