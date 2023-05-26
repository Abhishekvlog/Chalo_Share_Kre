package com.example.sharingwithcompose.pref

import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow

val USER_KEY = stringPreferencesKey("user_name")

interface UserPre {
    fun getUserName() : Flow<String>
    suspend fun saveName(name : String)
}