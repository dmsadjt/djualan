package com.example.djualan.ui.component

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

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
