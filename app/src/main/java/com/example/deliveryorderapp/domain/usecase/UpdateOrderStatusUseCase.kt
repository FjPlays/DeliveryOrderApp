package com.example.deliveryorderapp.domain.usecase

import com.example.deliveryorderapp.domain.model.Order
import com.example.deliveryorderapp.domain.model.OrderStatus
import com.example.deliveryorderapp.domain.repository.OrderRepository

class UpdateOrderStatusUseCase(private val repository: OrderRepository) {
    suspend operator fun invoke(id: String, status: OrderStatus): Result<Order> =
        repository.updateOrderStatus(id, status)
}