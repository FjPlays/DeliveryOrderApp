package com.example.deliveryorderapp.domain.repository

import com.example.deliveryorderapp.domain.model.Order
import com.example.deliveryorderapp.domain.model.OrderStatus

interface OrderRepository {
    suspend fun getOrders(): Result<List<Order>>
    suspend fun getOrdersByCustomer(customerId: String): Result<List<Order>>
    suspend fun getOrderDetail(id: String): Result<Order>
    suspend fun createOrder(
        customerId: String,
        customerName: String,
        itemName: String,
        description: String,
        price: String,
        deliveryAddress: String
    ): Result<Order>
    suspend fun updateOrderStatus(id: String, status: OrderStatus): Result<Order>
}