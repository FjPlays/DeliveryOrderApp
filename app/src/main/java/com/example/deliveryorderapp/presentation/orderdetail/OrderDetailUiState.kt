package com.example.deliveryorderapp.presentation.orderdetail

import com.example.deliveryorderapp.domain.model.Order
import com.example.deliveryorderapp.domain.model.OrderStatus

data class OrderListUiState(
    val isLoading: Boolean = false,
    val orders: List<Order> = emptyList(),
    val filteredOrders: List<Order> = emptyList(),
    val selectedFilter: OrderStatus? = null,
    val error: String? = null
)