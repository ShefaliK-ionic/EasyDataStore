package com.easydatastore.utils

import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppDataStore @Inject constructor(val dataStore: DataStore<Preferences>) {
    val EMP_ID_ = preferencesKey<Int>(EMP_ID)
    val EMP_NAME_ = preferencesKey<String>(EMP_NAME)

    suspend fun storeUser(id: Int, name: String) {
        dataStore.edit {
            it[EMP_ID_] = id
            it[EMP_NAME_] =name
        }
    }

    val userIdFlow: Flow<Int> = dataStore.data.map {
        it[EMP_ID_] ?: 0
    }

    // Create a name flow to retrieve name from the preferences
    val userNameFlow: Flow<String> = dataStore.data.map {
        it[EMP_NAME_] ?: ""
    }



}