package com.example.deliveryorderapp.di

import com.example.deliveryorderapp.domain.usecase.CreateOrderUseCase
import com.example.deliveryorderapp.domain.usecase.GetOrderDetailUseCase
import com.example.deliveryorderapp.domain.usecase.GetOrdersUseCase
import com.example.deliveryorderapp.domain.usecase.UpdateOrderStatusUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetOrdersUseCase(get()) }
    factory { GetOrderDetailUseCase(get()) }
    factory { CreateOrderUseCase(get()) }
    factory { UpdateOrderStatusUseCase(get()) }
}