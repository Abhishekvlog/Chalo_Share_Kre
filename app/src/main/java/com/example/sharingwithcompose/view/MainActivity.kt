package com.example.sharingwithcompose.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sharingwithcompose.navigation.NavGraph
import com.example.sharingwithcompose.ui.theme.SharingWithComposeTheme

class MainActivity : ComponentActivity() {
    lateinit var navController : NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SharingWithComposeTheme {
                navController = rememberNavController()
                NavGraph(navController)
            }
        }
    }
}
