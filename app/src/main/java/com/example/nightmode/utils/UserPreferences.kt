package com.example.nightmode.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences(context: Context) {
    private val applicationContext = context.applicationContext
    private val dataStore: DataStore<Preferences>

    init {
        dataStore = applicationContext.createDataStore(
            name = "user_pref"
        )
    }

    val getLanguage: Flow<String>
        get() = dataStore.data.map {
            it[KEY_SELECTED_LANGUAGE] ?: "tr"
        }

    suspend fun saveLanguage(string: String) {
        dataStore.edit {
            it[KEY_SELECTED_LANGUAGE] = string
        }
    }

    companion object {
        private val KEY_SELECTED_LANGUAGE = preferencesKey<String>("key_language")
    }
}