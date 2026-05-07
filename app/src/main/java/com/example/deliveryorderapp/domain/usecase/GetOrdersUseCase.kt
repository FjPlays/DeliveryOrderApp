package com.example.deliveryorderapp.domain.usecase

import com.example.deliveryorderapp.domain.model.Order
import com.example.deliveryorderapp.domain.repository.OrderRepository

class GetOrdersUseCase(private val repository: OrderRepository) {
    suspend operator fun invoke(): Result<List<Order>> = repository.getOrders()
}