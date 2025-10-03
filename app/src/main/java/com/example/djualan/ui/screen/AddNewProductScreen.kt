package com.example.djualan.ui.screen

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewProductScreen(navController: NavController) {
    val context = LocalContext.current
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
        var szImageuri by remember { mutableStateOf<Uri?>(null)}

        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { szUri : Uri? ->
            szImageuri = szUri
        }

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

            Button(onClick = {launcher.launch("image/")}) {
                Text("Pick Image")
            }

            szImageuri?.let {
                val bitmap = remember(szImageuri) {
                   try {
                       val source = ImageDecoder.createSource(context.contentResolver, it)
                       ImageDecoder.decodeBitmap(source)
                   } catch(e:Exception) {
                       null
                   }
                }

                bitmap?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = "Product Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentScale = ContentScale.Crop
                    )
                }

            }

        }
    }
}

@Composable
fun Button() {
    TODO("Not yet implemented")
}