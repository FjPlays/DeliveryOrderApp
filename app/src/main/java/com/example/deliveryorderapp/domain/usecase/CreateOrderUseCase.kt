package com.example.deliveryorderapp.domain.usecase

import com.example.deliveryorderapp.domain.model.Order
import com.example.deliveryorderapp.domain.repository.OrderRepository

class CreateOrderUseCase(private val repository: OrderRepository) {
    suspend operator fun invoke(
        customerId: String,
        customerName: String,
        itemName: String,
        description: String,
        price: String,
        deliveryAddress: String
    ): Result<Order> = repository.createOrder(
        customerId, customerName, itemName, description, price, deliveryAddress
    )
}