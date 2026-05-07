package com.example.deliveryorderapp

import com.example.deliveryorderapp.domain.model.Order
import com.example.deliveryorderapp.domain.model.OrderStatus
import com.example.deliveryorderapp.domain.usecase.GetOrdersUseCase
import com.example.deliveryorderapp.domain.usecase.UpdateOrderStatusUseCase
import com.example.deliveryorderapp.presentation.orderlist.OrderListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class OrderListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val getOrdersUseCase = mockk<GetOrdersUseCase>()
    private val updateStatusUseCase = mockk<UpdateOrderStatusUseCase>()

    private val fakeOrders = listOf(
        Order("1", "c1", "Alice", "Shoes", "desc", "500", "1st Ave", OrderStatus.PENDING, "2024-01-01"),
        Order("2", "c2", "Bob",   "Watch", "desc", "999", "2nd Ave", OrderStatus.DELIVERED, "2024-01-02")
    )

    @Before
    fun setup() { Dispatchers.setMain(testDispatcher) }

    @After
    fun tearDown() { Dispatchers.resetMain() }

    @Test
    fun `initial load populates orders`() = runTest {
        coEvery { getOrdersUseCase() } returns Result.success(fakeOrders)
        val vm = OrderListViewModel(getOrdersUseCase, updateStatusUseCase)
        advanceUntilIdle()
        assertEquals(2, vm.uiState.value.orders.size)
        assertFalse(vm.uiState.value.isLoading)
    }

    @Test
    fun `filter by PENDING shows only pending orders`() = runTest {
        coEvery { getOrdersUseCase() } returns Result.success(fakeOrders)
        val vm = OrderListViewModel(getOrdersUseCase, updateStatusUseCase)
        advanceUntilIdle()
        vm.filterByStatus(OrderStatus.PENDING)
        val filtered = vm.uiState.value.filteredOrders
        assertTrue(filtered.all { it.status == OrderStatus.PENDING })
        assertEquals(1, filtered.size)
    }

    @Test
    fun `error state set on failure`() = runTest {
        coEvery { getOrdersUseCase() } returns Result.failure(Exception("timeout"))
        val vm = OrderListViewModel(getOrdersUseCase, updateStatusUseCase)
        advanceUntilIdle()
        assertNotNull(vm.uiState.value.error)
        assertTrue(vm.uiState.value.orders.isEmpty())
    }
}