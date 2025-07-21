package com.gwabs.movematecloneapp.ui.screens



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gwabs.movematecloneapp.R

@Composable
fun EstimateResultScreen(amount: String, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "MoveMate",
            color = Color(0xFF5C14E4),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )

        Image(
            painter = painterResource(id = R.drawable.outline_electric_bike_24),
            contentDescription = null,
            modifier = Modifier.size(160.dp).padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Total Estimated Amount",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = "$${amount} USD",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1BA87C),
            modifier = Modifier.padding(top = 4.dp)
        )

        Text(
            text = "This amount is estimated. It will vary\nif you change your location or weight",
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { navController.popBackStack() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF79E1B)),
            shape = RoundedCornerShape(50),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to home", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EstimateResultScreenPreview(){
     EstimateResultScreen(amount = "1460", navController = rememberNavController())
}
