package com.example.deliveryorderapp.domain.model

data class Order(
    val id: String,
    val customerId: String,
    val customerName: String,
    val itemName: String,
    val description: String,
    val price: String,
    val deliveryAddress: String,
    val status: OrderStatus,
    val createdAt: String
)