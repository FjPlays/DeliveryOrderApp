package com.example.deliveryorderapp.di

import com.example.deliveryorderapp.data.repository.OrderRepositoryImpl
import com.example.deliveryorderapp.domain.repository.OrderRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<OrderRepository> { OrderRepositoryImpl(get()) }
}
