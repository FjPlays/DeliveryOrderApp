package com.example.deliveryorderapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.deliveryorderapp.domain.model.OrderStatus

@Composable
fun StatusFilterBar(
    selectedFilter: OrderStatus?,
    onFilterSelected: (OrderStatus?) -> Unit
) {
    val filters = listOf(null) + OrderStatus.entries
    //Default status will be entries (all data no pagination for now)
    //We can filter the status such as Pending, In Transit, Delivered
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(filters) { filter ->
            FilterChip(
                selected = selectedFilter == filter,
                onClick = { onFilterSelected(filter) },
                label = { Text(filter?.name?.replace("_", " ") ?: "All") }
            )
        }
    }
}