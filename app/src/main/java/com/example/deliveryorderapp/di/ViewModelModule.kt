package com.example.deliveryorderapp.di

import com.example.deliveryorderapp.presentation.orderdetail.OrderDetailViewModel
import com.example.deliveryorderapp.presentation.orderlist.OrderListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { OrderListViewModel(get(), get()) }
    viewModel { parameters -> OrderDetailViewModel(get(), get(), parameters.get()) }
}