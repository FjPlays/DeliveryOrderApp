package com.example.deliveryorderapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateOrderRequest(
    val customerId: String,
    val customerName: String,
    val itemName: String,
    val description: String,
    val price: String,
    val deliveryAddress: String,
    val status: String = "PENDING"
)