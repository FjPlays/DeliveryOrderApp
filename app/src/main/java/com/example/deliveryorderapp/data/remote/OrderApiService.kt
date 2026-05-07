package com.example.deliveryorderapp.data.remote

import com.example.deliveryorderapp.data.remote.dto.CreateOrderRequest
import com.example.deliveryorderapp.data.remote.dto.OrderDto
import com.example.deliveryorderapp.data.remote.dto.UpdateStatusRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.client.request.put
import io.ktor.client.request.setBody

class OrderApiService(private val client: HttpClient) {

    private val baseUrl = "https://69fc1b2ffce564e259174a43.mockapi.io/api/v1"

    suspend fun getOrders(): List<OrderDto> =
        client.get("$baseUrl/orders").body()

    suspend fun getOrdersByCustomer(customerId: String): List<OrderDto> =
        client.get("$baseUrl/orders") {
            parameter("customerId", customerId)
        }.body()

    suspend fun getOrderDetail(id: String): OrderDto =
        client.get("$baseUrl/orders/$id").body()

    suspend fun createOrder(request: CreateOrderRequest): OrderDto =
        client.post("$baseUrl/orders") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()

    suspend fun updateOrderStatus(id: String, request: UpdateStatusRequest): OrderDto =
        client.put("$baseUrl/orders/$id") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
}