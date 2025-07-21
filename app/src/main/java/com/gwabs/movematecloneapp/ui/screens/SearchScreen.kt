package com.gwabs.movematecloneapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.gwabs.movematecloneapp.ui.components.search.SearchBarSection
import com.gwabs.movematecloneapp.ui.components.search.SearchResultItem
import com.gwabs.movematecloneapp.ui.data.dummyResults
import com.gwabs.movematecloneapp.ui.navigation.Screen
import com.gwabs.movematecloneapp.ui.theme.appbarBg


@Composable
fun SearchScreen(navController: NavHostController) {
    val query = remember { mutableStateOf("") }


    val filteredResults = remember(query.value) {
        dummyResults.filter {
            it.first.contains(query.value, ignoreCase = true) || it.second.contains(query.value, ignoreCase = true)
        }
    }
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = appbarBg
                    ),
            ) {

                Spacer(Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth().padding(top = 16.dp, bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    IconButton(onClick = {navController.navigate(Screen.Home.route) }) {
                        Icon(
                            Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(40.dp)
                        )
                    }


                    SearchBarSection(query.value) { newQuery ->
                        query.value = newQuery
                    }
                }

            }


        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            items(filteredResults) { item ->
                Spacer(Modifier.height(16.dp))
                SearchResultItem(title = item.first, tracking = item.second, route = item.third)
                Spacer(modifier = Modifier.height(12.dp))
            }


        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SearchScreenPreview() {
    SearchScreen(rememberNavController())
}