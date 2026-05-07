package com.example.deliveryorderapp

import com.example.deliveryorderapp.domain.model.Order
import com.example.deliveryorderapp.domain.model.OrderStatus
import com.example.deliveryorderapp.domain.repository.OrderRepository
import com.example.deliveryorderapp.domain.usecase.GetOrdersUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GetOrdersUseCaseTest {

    private val repository = mockk<OrderRepository>()
    private val useCase = GetOrdersUseCase(repository)

    private val fakeOrders = listOf(
        Order("1", "c1", "John", "Book", "A book", "10.00", "123 St", OrderStatus.PENDING, "2024-01-01"),
        Order("2", "c1", "John", "Pen",  "A pen",  "5.00",  "123 St", OrderStatus.DELIVERED, "2024-01-02")
    )

    @Test
    fun `returns orders on success`() = runTest {
        coEvery { repository.getOrders() } returns Result.success(fakeOrders)
        val result = useCase()
        assertTrue(result.isSuccess)
        assertEquals(2, result.getOrThrow().size)
    }

    @Test
    fun `returns failure when repository throws`() = runTest {
        coEvery { repository.getOrders() } returns Result.failure(Exception("Network error"))
        val result = useCase()
        assertTrue(result.isFailure)
    }
}