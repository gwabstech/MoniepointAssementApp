package com.gwabs.movematecloneapp.ui.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.TextButton
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gwabs.movematecloneapp.R

// home Components
@Composable
fun TopBarSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_person_pin_24),
            contentDescription = "Avatar",
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text("Location", color = Color.White, style = MaterialTheme.typography.labelSmall)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    "Abuja, Nigeria",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { /* Notifications */ }) {
            Icon(
                Icons.Default.Notifications,
                contentDescription = "Notifications",
                tint = Color.White
            )
        }
    }
}


@Composable
fun SearchBarSection(onclick: () -> Unit) {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        modifier = Modifier
            .clickable{onclick()}
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        enabled = false,
        placeholder = { Text("Enter the receipt number ...") },
        leadingIcon = { Icon(Icons.Default.Search, tint = Color(0xFF882404), contentDescription = null) },
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.outline_barcode_scanner_24),
                contentDescription = "Avatar",
                tint =  Color(0xFF882404),
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
            )
        },
        shape = RoundedCornerShape(50),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.LightGray,
            unfocusedBorderColor = Color.LightGray,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
        )
    )
    Spacer(modifier = Modifier.height(16.dp))
}


@Composable
fun TrackingCard() {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {


                Column {
                    Text(
                        "Shipment Number",
                        color = Color.Black,
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text("NEJ20089934122231", style = MaterialTheme.typography.bodyLarge)
                }
                Icon(
                    painter = painterResource(R.drawable.outline_delivery_truck_bolt_24),
                    contentDescription = "shipment Box",
                    modifier = Modifier.size(50.dp),
                    tint = Color(0xFF882404)
                )
            }
            // #534591
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Sender", color = Color.Black, style = MaterialTheme.typography.labelSmall)
                    Text("Atlanta, 5243", style = MaterialTheme.typography.bodyMedium)
                }
                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                    Text("Time", color = Color.Black, style = MaterialTheme.typography.labelSmall)
                    Text(
                        "2 day -3 days",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "Receiver",
                        color = Color.Black,
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text("Chicago, 6342", style = MaterialTheme.typography.bodyMedium)
                }
                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                    Text("Status", color = Color.Black, style = MaterialTheme.typography.labelSmall)
                    Text("Waiting to collect", style = MaterialTheme.typography.bodyMedium)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            TextButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("+ Add Stop", color = Color(0xFF882404))
            }

        }
    }
}

@Composable
fun AvailableVehiclesSection() {
    Column(modifier = Modifier.padding(start = 16.dp)) {
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow {
            items(3) {
                when (it) {
                    0 -> VehicleCard("Freight", "Local", R.drawable.outline_electric_bike_24)
                    1 -> VehicleCard(
                        "Air freight",
                        "International",
                        R.drawable.outline_plane_contrails_24
                    )

                    2 -> VehicleCard(
                        "Train freight",
                        "Multi Service",
                        R.drawable.outline_delivery_truck_speed_24
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}


@Composable
fun VehicleCard(title: String, subtitle: String, imageRes: Int) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .padding(end = 12.dp)
            .size(width = 160.dp, height = 170.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {


            Text(title, style = MaterialTheme.typography.titleSmall)
            Text(subtitle, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,

                modifier = Modifier
                    .height(170.dp)
                    .fillMaxWidth()
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ComponentsPreview() {

    Column(
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xCD1C05F6), // A slightly lighter/brighter shade of your base color
                    Color(0xCD2616C2),  // Your base color
                    Color(0xCD1C05F6),
                )
            )
        )
    ) {
        TopBarSection()
        SearchBarSection(){

        }
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color.White)
        ) {
            TrackingCard()
            AvailableVehiclesSection()

        }

    }
    // TopBarSection()
}