package com.gwabs.movematecloneapp.ui.components.shipment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.gwabs.movematecloneapp.ui.data.Shipment

@Composable
fun ShipmentFilterTabs(
    tabs: List<String>,
    selectedTab: MutableState<Int>
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTab.value,
        containerColor = Color.White,
        edgePadding = 16.dp,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(tabPositions[selectedTab.value]),
                color = Color(0xFFFF4081)
            )
        }
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                selected = selectedTab.value == index,
                onClick = { selectedTab.value = index },
                text = {
                    Text(
                        text = tab,
                        color = if (selectedTab.value == index) Color.Black else Color.Gray,
                        fontWeight = if (selectedTab.value == index) FontWeight.Bold else FontWeight.Normal
                    )
                }
            )
        }
    }
}


@Composable
fun ShipmentItem(shipment: Shipment) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Status Badge
        Column(
            modifier = Modifier.weight(1f)
        ) {
            ShipmentStatusBadge(status = shipment.status)

            Spacer(modifier = Modifier.height(8.dp))

            Text("Arriving today!", style = MaterialTheme.typography.titleSmall)
            Text(
                "Your delivery, ${shipment.trackingNumber} from ${shipment.fromCity}, is arriving today!",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(shipment.amount, fontWeight = FontWeight.Bold, color = Color(0xFF5C14E4))
            Text(shipment.date, color = Color.Gray, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            Icons.Default.ShoppingCart,
            contentDescription = null,
            modifier = Modifier.size(40.dp),
            tint =  Color(0xFF882404)
        )
    }
}


@Composable
fun ShipmentStatusBadge(status: String) {
    val (bg, label) = when (status.lowercase()) {
        "loading" -> Color(0xFFE3F2FD) to "🔄 Loading"
        "in-progress" -> Color(0xFFE8F5E9) to "🚚 In Progress"
        "completed" -> Color(0xFFD1C4E9) to "✅ Completed"
        "pending" -> Color(0xFFFFF3E0) to "⏳ Pending"
        else -> Color.LightGray to status
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(bg)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(label, style = MaterialTheme.typography.bodySmall)
    }
}

