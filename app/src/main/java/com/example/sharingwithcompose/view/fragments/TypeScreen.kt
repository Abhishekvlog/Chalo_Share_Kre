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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sharingwithcompose.navigation.Screens
import com.example.sharingwithcompose.view.UserNameViewModel

@Composable
fun TypeScreen(navController: NavController) {
    val userViewModel = hiltViewModel<UserNameViewModel>()
    val userName = userViewModel.userName.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Hi ${userName.value}", modifier = Modifier.padding(start = 12.dp))
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
