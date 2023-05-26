package com.example.sharingwithcompose.view.fragments

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.sharingwithcompose.view.PermissionRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun StorageScreen() {
    val corutineScope = rememberCoroutineScope()
    var selectImageUris by remember {
        mutableStateOf<List<Uri>>(emptyList())
    }
    // handle pic multiple items
    val multiplePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uris -> selectImageUris = uris }
    )
    // check storage permission is granted or not
    PermissionRequest() { value ->
        if (value) {
            corutineScope.launch {
                delay(3000)
                multiplePhotoPicker.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            }
        }

    }
    Column(modifier = Modifier.fillMaxSize()) {

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(selectImageUris) { uri ->
                AsyncImage(
                    model = uri,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )

            }
        }
    }
}

