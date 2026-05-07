package com.example.deliveryorderapp.domain.usecase

import com.example.deliveryorderapp.domain.model.Order
import com.example.deliveryorderapp.domain.repository.OrderRepository

class GetOrderDetailUseCase(private val repository: OrderRepository) {
    suspend operator fun invoke(id: String): Result<Order> = repository.getOrderDetail(id)
}