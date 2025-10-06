package com.example.djualan.ui.screen

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.widget.Toast
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.djualan.R
import com.example.djualan.R.*
import com.example.djualan.data.dao.Product
import com.example.djualan.data.repository.ProductRepository
import com.example.djualan.viewmodel.ProductViewModel
import com.example.djualan.viewmodel.factory.ProductViewModelFactory
import java.util.UUID

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewProductScreen(
    navController: NavController,
    repository: ProductRepository,
    viewModel: ProductViewModel = viewModel(factory = ProductViewModelFactory(repository))
) {
    val context = LocalContext.current
    var szName by remember { mutableStateOf("") }
    var szPrice by remember { mutableStateOf("") }
    var bActive by remember { mutableStateOf(false) }
    var szImageUri by remember { mutableStateOf<Uri?>(null)}
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { szUri : Uri? ->
        szImageUri = szUri
    }
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {Text(stringResource(string.label_add_new_product)) },
                actions = {
                    IconButton(onClick = {
                        val productData = Product(
                            szProductId = UUID.randomUUID().toString(),
                            szProductName = szName,
                            decPrice = szPrice.toDouble(),
                            bActive = bActive,
                            szImageUri = szImageUri?.toString()
                        )

                        viewModel.addProduct(productData) { bSuccess, szMessage ->
                            val szMessage = if (bSuccess) context.getString(R.string.msg_product_added) else szMessage
                            Toast.makeText(context, szMessage, Toast.LENGTH_SHORT).show()
                            if(bSuccess)
                                navController.popBackStack()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Product"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = szName,
                onValueChange = {szName = it},
                label = { Text(stringResource(R.string.flabel_product_name))},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            OutlinedTextField(
                value = szPrice,
                onValueChange = {szPrice = it},
                label = { Text(stringResource(string.flabel_product_price))},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = bActive,
                    onCheckedChange = { bActive = it }
                )
                Text(stringResource(string.flabel_active))
            }

            Button(
                onClick = {launcher.launch("image/")},
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Pick Image")
            }

            szImageUri?.let {
                val bitmap = remember(szImageUri) {
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
                            .padding(8.dp)
                            .fillMaxWidth()
                            .height(200.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}