package com.example.deliveryorderapp.data.repository

import com.example.deliveryorderapp.data.mapper.toDomain
import com.example.deliveryorderapp.data.remote.OrderApiService
import com.example.deliveryorderapp.data.remote.dto.CreateOrderRequest
import com.example.deliveryorderapp.data.remote.dto.UpdateStatusRequest
import com.example.deliveryorderapp.domain.model.Order
import com.example.deliveryorderapp.domain.model.OrderStatus
import com.example.deliveryorderapp.domain.repository.OrderRepository

class OrderRepositoryImpl(
    private val apiService: OrderApiService
) : OrderRepository {

    override suspend fun getOrders(): Result<List<Order>> = runCatching {
        apiService.getOrders().map { it.toDomain() }
    }

    override suspend fun getOrdersByCustomer(customerId: String): Result<List<Order>> = runCatching {
        apiService.getOrdersByCustomer(customerId).map { it.toDomain() }
    }

    override suspend fun getOrderDetail(id: String): Result<Order> = runCatching {
        apiService.getOrderDetail(id).toDomain()
    }

    override suspend fun createOrder(
        customerId: String,
        customerName: String,
        itemName: String,
        description: String,
        price: String,
        deliveryAddress: String
    ): Result<Order> = runCatching {
        apiService.createOrder(
            CreateOrderRequest(
                customerId,
                customerName,
                itemName,
                description,
                price,
                deliveryAddress
            )
        ).toDomain()
    }

    override suspend fun updateOrderStatus(id: String, status: OrderStatus): Result<Order> = runCatching {
        apiService.updateOrderStatus(id, UpdateStatusRequest(status.name)).toDomain()
    }
}