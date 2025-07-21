package com.gwabs.movematecloneapp.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.gwabs.movematecloneapp.ui.components.calculate.BoxCardInput
import com.gwabs.movematecloneapp.ui.components.calculate.DropdownSelector
import com.gwabs.movematecloneapp.ui.navigation.Screen
import com.gwabs.movematecloneapp.ui.theme.appbarBg

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CalculateShipmentScreen(navController: NavHostController) {
    var senderLocation by remember { mutableStateOf("") }
    var receiverLocation by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }

    var packaging by remember { mutableStateOf("Box") }
    val packagingOptions = listOf("Box", "Envelope", "Tube", "Crate")

    var selectedCategory by remember { mutableStateOf("") }
    val categories =
        listOf("Documents", "Glass", "Liquid", "Food", "Electronic", "Product", "Others")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)

    ) {
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
                        "Calculate",

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
                    IconButton(onClick = {navController.navigate(Screen.Home.route) }) {
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
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            "Destination",
            modifier = Modifier.padding(start = 8.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(8.dp))
        BoxCardInput("Sender location", senderLocation) { senderLocation = it }
        BoxCardInput("Receiver location", receiverLocation) { receiverLocation = it }
        BoxCardInput("Approx weight (kg)", weight, keyboardType = KeyboardType.Number) {
            weight = it
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            "Packaging",
            modifier = Modifier.padding(start = 8.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Text(
            "What are you sending?",
            modifier = Modifier.padding(start = 8.dp),
            fontSize = 13.sp,
            color = Color.Gray
        )

        DropdownSelector(selected = packaging, options = packagingOptions) {
            packaging = it
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            "Categories",
            modifier = Modifier.padding(start = 8.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Text(
            "What are you sending?",
            modifier = Modifier.padding(start = 8.dp),
            fontSize = 13.sp,
            color = Color.Gray
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(top = 8.dp, start = 10.dp)
        ) {
            categories.forEach { category ->
                FilterChip(
                    selected = selectedCategory == category,
                    onClick = {
                        selectedCategory = if (selectedCategory == category) "" else category
                    },

                    label = { Text(category) }
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                navController.navigate(Screen.EstimateResult.createRoute("1460"))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 16.dp),

            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF79E1B)),
            shape = RoundedCornerShape(50)
        ) {
            Text("Calculate", color = Color.White)
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CalculateShipmentScreenPreview() {
    CalculateShipmentScreen(rememberNavController())
}