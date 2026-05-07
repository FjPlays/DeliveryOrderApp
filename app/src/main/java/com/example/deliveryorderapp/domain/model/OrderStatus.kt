package com.example.deliveryorderapp.domain.model

enum class OrderStatus {
    PENDING,
    IN_TRANSIT,
    DELIVERED;

    companion object {
        fun fromString(value: String): OrderStatus = when (value.uppercase()) {
            "IN_TRANSIT" -> IN_TRANSIT
            "DELIVERED"  -> DELIVERED
            else         -> PENDING
        }
    }
}