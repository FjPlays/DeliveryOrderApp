package com.example.deliveryorderapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class UpdateStatusRequest(
    val status: String
)