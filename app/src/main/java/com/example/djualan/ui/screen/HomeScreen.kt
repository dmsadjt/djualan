package com.example.djualan.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.djualan.ui.component.Fab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = { Fab(modifier = Modifier, {navController.navigate("product")}) },
        topBar = { TopAppBar(title = { Text("Djualan") }) })
    { innerPadding ->
        Text(modifier = Modifier.padding(innerPadding), text = "Djualan Main Page")
    }
}
