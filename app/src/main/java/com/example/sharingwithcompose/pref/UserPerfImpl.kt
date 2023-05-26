package com.example.sharingwithcompose.pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class UserPerfImpl(private val dataStore: DataStore<Preferences>) : UserPre {
    override fun getUserName(): Flow<String> {
        return dataStore.data.catch { emit(emptyPreferences()) }.map {
            it[USER_KEY] ?: ""
        }
    }

    override suspend fun saveName(name: String) {
        dataStore.edit {
            it[USER_KEY] = name
        }
    }

}