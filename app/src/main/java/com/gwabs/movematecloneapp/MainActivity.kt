package com.gwabs.movematecloneapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.gwabs.movematecloneapp.ui.navigation.AppNavigation
import com.gwabs.movematecloneapp.ui.screens.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation()

        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}