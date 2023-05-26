package com.example.sharingwithcompose.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sharingwithcompose.pref.UserPre
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserNameViewModel @Inject constructor(private val userPerf: UserPre) : ViewModel() {

    val userName = userPerf.getUserName().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ""
    )

    fun saveData(name: String) {
        viewModelScope.launch {
            userPerf.saveName(name)
        }
    }
}