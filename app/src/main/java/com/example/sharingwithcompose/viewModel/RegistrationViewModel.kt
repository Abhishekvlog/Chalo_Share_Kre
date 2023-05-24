package com.example.sharingwithcompose.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class RegistrationViewModel() : ViewModel(){
    val name = mutableStateOf("")
}