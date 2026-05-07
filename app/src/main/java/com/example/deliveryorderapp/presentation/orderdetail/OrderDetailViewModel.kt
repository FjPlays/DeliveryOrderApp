package com.example.deliveryorderapp.presentation.orderdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliveryorderapp.domain.model.OrderStatus
import com.example.deliveryorderapp.domain.usecase.GetOrderDetailUseCase
import com.example.deliveryorderapp.domain.usecase.UpdateOrderStatusUseCase
import com.example.deliveryorderapp.presentation.orderlist.OrderDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OrderDetailViewModel(
    private val getOrderDetailUseCase: GetOrderDetailUseCase,
    private val updateOrderStatusUseCase: UpdateOrderStatusUseCase,
    private val orderId: String
) : ViewModel() {

    private val _uiState = MutableStateFlow(OrderDetailUiState())
    val uiState: StateFlow<OrderDetailUiState> = _uiState.asStateFlow()

    init { loadOrder() }

    fun loadOrder() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            getOrderDetailUseCase(orderId)
                .onSuccess { order ->
                    _uiState.update { it.copy(isLoading = false, order = order) }
                }
                .onFailure { error ->
                    _uiState.update { it.copy(isLoading = false, error = error.message) }
                }
        }
    }

    fun updateStatus(status: OrderStatus) {
        viewModelScope.launch {
            _uiState.update { it.copy(isUpdating = true) }
            updateOrderStatusUseCase(orderId, status)
                .onSuccess { updated ->
                    _uiState.update { it.copy(isUpdating = false, order = updated, updateSuccess = true) }
                }
                .onFailure { error ->
                    _uiState.update { it.copy(isUpdating = false, error = error.message) }
                }
        }
    }
}