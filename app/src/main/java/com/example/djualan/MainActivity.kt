package com.example.djualan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.djualan.ui.theme.DjualanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DjualanTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable("home") {
                        HomeScreen(navController = navController)
                    }
                    composable("product") {
                        ProductScreen(navController = navController)
                    }
                    composable("addProduct") {
                        AddNewProductScreen(navController = navController)
                    }
                }
            }
        }
    }
}


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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(navController: NavController) {
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {Fab(modifier = Modifier, {navController.navigate("addProduct")})},
        topBar = { TopAppBar(title = {Text("Produk")})
    }) { innerPadding ->
        Text(modifier = Modifier.padding(innerPadding), text = "Djualan Product Page")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewProductScreen(navController: NavController) {
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {Text("Add new product") },
                actions = {
                    IconButton(onClick = { /** TODO: Handle Save **/}) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Product"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        var szName by remember { mutableStateOf("") }
        var szPrice by remember { mutableStateOf("") }
        var bActive by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = szName,
                onValueChange = {szName = it},
                label = { Text("Product Name")},
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )

            OutlinedTextField(
                value = szPrice,
                onValueChange = {szPrice = it},
                label = { Text("Product Price")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )

            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = bActive,
                    onCheckedChange = { bActive = it }
                )
                Text("Active")
            }
        }
    }
}


@Composable
fun Fab(modifier: Modifier, action: () -> Unit) {
    val context = LocalContext.current
    FloatingActionButton(
        modifier = modifier,
        onClick = action,
        shape = CircleShape,
        containerColor = Color.Blue,
        contentColor = Color.White,
        content = {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Fab")
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DjualanTheme {
    }
}