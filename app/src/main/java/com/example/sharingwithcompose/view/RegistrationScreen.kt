package com.example.sharingwithcompose.view

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sharingwithcompose.utils.name

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun RegistrationScreen() {
    var nam = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Enter your name", modifier = Modifier.padding(start = 12.dp))
        textField(nam = nam.value, hint = "User Name") {
            nam.value = it
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {name = nam.value},
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
Box(modifier = Modifier.fillMaxWidth()){
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