package com.example.sharingwithcompose.view.fragments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sharingwithcompose.navigation.Screens
import com.example.sharingwithcompose.utils.name

@Composable
fun TypeScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Hi ${name}", modifier = Modifier.padding(start = 12.dp))
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            buttonView(name = "As Sender") {
                navController.navigate(Screens.storageScreen.route)
            }
            buttonView(name = "As Receiver") {}
        }
    }
}

@Composable
fun buttonView(name: String, onClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Button(onClick = { onClick() }, modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)) {
            Text(text = name)
        }
    }
}
