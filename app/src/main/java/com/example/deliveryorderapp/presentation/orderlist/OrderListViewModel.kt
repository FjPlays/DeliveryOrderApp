package com.example.deliveryorderapp.presentation.orderlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliveryorderapp.domain.model.Order
import com.example.deliveryorderapp.domain.model.OrderStatus
import com.example.deliveryorderapp.domain.usecase.GetOrderDetailUseCase
import com.example.deliveryorderapp.domain.usecase.GetOrdersUseCase
import com.example.deliveryorderapp.domain.usecase.UpdateOrderStatusUseCase
import com.example.deliveryorderapp.presentation.orderdetail.OrderListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OrderListViewModel(
    private val getOrdersUseCase: GetOrdersUseCase,
    private val updateOrderStatusUseCase: UpdateOrderStatusUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(OrderListUiState())
    val uiState: StateFlow<OrderListUiState> = _uiState.asStateFlow()

    init { loadOrders() }

    fun loadOrders() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            getOrdersUseCase()
                .onSuccess { orders ->
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            orders = orders,
                            filteredOrders = applyFilter(orders, state.selectedFilter)
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update { it.copy(isLoading = false, error = error.message) }
                }
        }
    }

    fun filterByStatus(status: OrderStatus?) {
        _uiState.update { state ->
            state.copy(
                selectedFilter = status,
                filteredOrders = applyFilter(state.orders, status)
            )
        }
    }

    fun updateStatus(orderId: String, status: OrderStatus) {
        viewModelScope.launch {
            updateOrderStatusUseCase(orderId, status)
                .onSuccess { loadOrders() }
                .onFailure { error ->
                    _uiState.update { it.copy(error = error.message) }
                }
        }
    }

    private fun applyFilter(orders: List<Order>, filter: OrderStatus?) =
        if (filter == null) orders else orders.filter { it.status == filter }
}