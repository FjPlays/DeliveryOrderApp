package com.example.deliveryorderapp.presentation.orderlist

import com.example.deliveryorderapp.domain.model.Order

data class OrderDetailUiState(
    val isLoading: Boolean = false,
    val order: Order? = null,
    val isUpdating: Boolean = false,
    val error: String? = null,
    val updateSuccess: Boolean = false
)