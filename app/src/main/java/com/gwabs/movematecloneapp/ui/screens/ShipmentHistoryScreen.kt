package com.gwabs.movematecloneapp.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.gwabs.movematecloneapp.ui.data.allShipments
import com.gwabs.movematecloneapp.ui.components.shipment.ShipmentFilterTabs
import com.gwabs.movematecloneapp.ui.components.shipment.ShipmentItem
import com.gwabs.movematecloneapp.ui.navigation.Screen
import com.gwabs.movematecloneapp.ui.theme.appbarBg

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun ShipmentHistoryScreen(navController: NavHostController) {
    val selectedTab = remember { mutableIntStateOf(0) }


    val tabs = listOf("All", "Completed", "In progress", "Pending")
    val filteredShipments = when (selectedTab.intValue) {
        1 -> allShipments.filter { it.status == "completed" }
        2 -> allShipments.filter { it.status == "in-progress" }
        3 -> allShipments.filter { it.status == "pending" }
        else -> allShipments
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(brush = appbarBg)
                .height(100.dp),
            contentAlignment = Alignment.Center
        ) {
            TopAppBar(
                title = {
                    Text(
                        "Shipment history",

                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()

                    )
                },
                modifier = Modifier
                    .background(Color.Transparent),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Screen.Home.route) }) {
                        Icon(
                            Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(40.dp)
                        )
                    }

                }
            )

        }

        ShipmentFilterTabs(tabs, selectedTab)


        AnimatedContent(
            targetState = selectedTab.value,
            transitionSpec = {
                slideInHorizontally { width -> width } + fadeIn() with
                        slideOutHorizontally { width -> -width } + fadeOut()
            },
            label = "TabTransition"
        ) { targetTab ->
            val filteredShipments = when (targetTab) {
                1 -> allShipments.filter { it.status == "completed" }
                2 -> allShipments.filter { it.status == "in-progress" }
                3 -> allShipments.filter { it.status == "pending" }
                else -> allShipments
            }

            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(filteredShipments) { shipment ->
                    ShipmentItem(shipment)
                }
            }
        }
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(filteredShipments) { shipment ->
                ShipmentItem(shipment)
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ShipmentHistoryScreenPreview() {
    ShipmentHistoryScreen(rememberNavController())
}