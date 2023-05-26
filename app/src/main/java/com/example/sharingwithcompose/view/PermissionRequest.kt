package com.example.sharingwithcompose.view

import android.Manifest
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@Composable
fun PermissionRequest(update : (Boolean) -> Unit) {
    val context = LocalContext.current
    // if Android api level is greater then 30 use this permission
    val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        arrayOf(
            Manifest.permission.MANAGE_EXTERNAL_STORAGE
        )
    } // Android api level is less then 30 use these permission
    else {
        arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

    val launchMultiplePermission =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permission ->
            val areGranted = permission.values.reduce { acc, next -> acc && next }
            if (areGranted) {
                update(true)

                Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }

    if (permission.all {
            ContextCompat.checkSelfPermission(
                context, it
            ) == PackageManager.PERMISSION_GRANTED
        }) {
        // permission granted
    } else {
        LaunchedEffect(true) {
            Log.d(TAG, "PermissionRequest: ")
            launchMultiplePermission.launch(permission)
        }
    }
}