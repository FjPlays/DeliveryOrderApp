package com.example.deliveryorderapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.deliveryorderapp.domain.model.OrderStatus

/*
 This is like the color code of the order status
 Green  = Delivered
 Yellow = Pending
 Blue = In Transit
*/
@Composable
fun StatusChip(status: OrderStatus) {
    val (bgColor, textColor) = when (status) {
        OrderStatus.PENDING    -> Color(0xFFFFF3CD) to Color(0xFF856404)
        OrderStatus.IN_TRANSIT -> Color(0xFFCCE5FF) to Color(0xFF004085)
        OrderStatus.DELIVERED  -> Color(0xFFD4EDDA) to Color(0xFF155724)
    }
    Text(
        text = status.name.replace("_", " "),
        modifier = Modifier
            .background(bgColor, RoundedCornerShape(12.dp))
            .padding(horizontal = 10.dp, vertical = 4.dp),
        color = textColor,
        style = MaterialTheme.typography.labelSmall
    )
}