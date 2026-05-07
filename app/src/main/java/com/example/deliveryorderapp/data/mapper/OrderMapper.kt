package com.example.deliveryorderapp.data.mapper

import com.example.deliveryorderapp.data.remote.dto.OrderDto
import com.example.deliveryorderapp.domain.model.Order
import com.example.deliveryorderapp.domain.model.OrderStatus

fun OrderDto.toDomain(): Order = Order(
    id = id,
    customerId = customerId,
    customerName = customerName,
    itemName = itemName,
    description = description,
    price = price,
    deliveryAddress = deliveryAddress,
    status = OrderStatus.fromString(status),
    createdAt = createdAt
)