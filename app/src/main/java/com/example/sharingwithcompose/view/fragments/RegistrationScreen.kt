package com.example.sharingwithcompose.view.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.sharingwithcompose.navigation.Screens
import com.example.sharingwithcompose.utils.name
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.delay

@SuppressLint("UnrememberedMutableState")
@Composable
fun RegistrationScreen(navController: NavController) {

    var locationCallback: LocationCallback? = null
    var fusedLocationClient: FusedLocationProviderClient? = null
    var locationRequired = false

    var nam = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            for (lo in p0.locations) {
                // Update UI with location data
            }
        }
    }

    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
        if (areGranted) {
            locationRequired = true
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }
    Column(modifier = Modifier.fillMaxSize()) {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
        )
        if (permissions.all {
                ContextCompat.checkSelfPermission(
                    context,
                    it
                ) != PackageManager.PERMISSION_GRANTED
            }) {
            LaunchedEffect(true) {
                delay(2000)
                launcherMultiplePermissions.launch(permissions)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Enter your name", modifier = Modifier.padding(start = 12.dp))
        textField(nam = nam.value, hint = "User Name") {
            nam.value = it
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                if (nam.value.isNullOrEmpty()){
                    Toast.makeText(context, "Please entre your name first", Toast.LENGTH_SHORT)
                        .show()
                }else {
                    name = nam.value
                    navController.navigate(Screens.typeScreen.route)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(8.dp),
        ) {
            Text(text = "Save")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun textField(nam: String, hint: String, update: (String) -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(value = nam, onValueChange = {
            update(it)
        },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                disabledBorderColor = Color.LightGray,
                focusedBorderColor = Color.Gray,
                errorBorderColor = Color.Red,
                cursorColor = Color.DarkGray,
                focusedLabelColor = Color.Gray,
            ), label = {
                Text(text = hint)
            })
    }
}
