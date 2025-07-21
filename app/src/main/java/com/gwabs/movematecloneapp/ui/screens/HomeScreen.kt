package com.gwabs.movematecloneapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.gwabs.movematecloneapp.ui.components.home.AvailableVehiclesSection
import com.gwabs.movematecloneapp.ui.components.home.SearchBarSection
import com.gwabs.movematecloneapp.ui.components.home.TopBarSection
import com.gwabs.movematecloneapp.ui.components.home.TrackingCard
import com.gwabs.movematecloneapp.ui.navigation.Screen
import com.gwabs.movematecloneapp.ui.theme.appbarBg

@Composable
fun HomeScreen(navController: NavHostController) {

    val scrollState = rememberLazyListState()
    var isAppBarVisible by remember { mutableStateOf(true) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                if (available.y < -10) {
                    isAppBarVisible = false // Scroll up → hide app bar
                } else if (available.y > 10) {
                    isAppBarVisible = true // Scroll down → show app bar
                }
                return Offset.Zero
            }
        }
    }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            Box(modifier = Modifier.background(MaterialTheme.colorScheme.primary)) {
                Column(
                    modifier = Modifier.background(
                        brush = appbarBg
                    )
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    TopBarSection()
                    AnimatedVisibility(
                        visible = isAppBarVisible,
                        modifier = Modifier.padding(vertical = 8.dp)

                        ) {
                        SearchBarSection(
                            onclick =  {
                                navController.navigate(Screen.Search.route)
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                }
            }
        },
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("Home") },
                    selected = true,
                    onClick = {
                        navController.navigate(Screen.Home.route)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.AddCircle, contentDescription = null) },
                    label = { Text("Calculate") },
                    selected = false,
                    onClick = {
                        navController.navigate(Screen.Calculate.route)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = null) },
                    label = { Text("Shipment") },
                    selected = false,
                    onClick = {
                        navController.navigate(Screen.Shipment.route)
                    }
                )

            }
        }

    ) { padding ->

        HomeContent(padding, scrollState, nestedScrollConnection)

    }
}

@Composable
private fun HomeContent(
    padding: PaddingValues,
    scrollState: androidx.compose.foundation.lazy.LazyListState,
    nestedScrollConnection: NestedScrollConnection
){

    Box(
        modifier = Modifier
            .nestedScroll(nestedScrollConnection)
            .padding(bottom = 16.dp)

    ) {
        LazyColumn(
            state = scrollState,
            modifier = Modifier
                .padding(padding)
                .background(color = Color.White)
        ) {
            item { TrackingCard() }
            item {
                Text(
                    "Available vehicles",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            item { AvailableVehiclesSection() }
            item { AvailableVehiclesSection() }


        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomePreview() {
    HomeScreen(rememberNavController())
}