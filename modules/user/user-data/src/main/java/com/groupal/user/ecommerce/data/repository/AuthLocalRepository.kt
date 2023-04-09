package com.groupal.user.ecommerce.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.groupal.shared.ecommerce.data.local.repository.ILocalRepository
import com.groupal.shared.ecommerce.data.local.repository.store
import com.groupal.user.ecommerce.data.IAuthLocalRepository
import com.groupal.user.ecommerce.domain.AuthLocalStorage
import com.groupal.user.ecommerce.domain.Login
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthLocalRepository(
    private val context: Context
) : IAuthLocalRepository, ILocalRepository<AuthLocalStorage> {
    private val Context.mposDataStore by preferencesDataStore(name = "auth")

    override val dataStore: DataStore<Preferences> get() = context.mposDataStore

    override suspend fun saveSession(login: Login) {
        dataStore.edit {
            it[PreferenceKeys.TOKEN] = login.sessionToken
        }
    }

    override suspend fun clear() {
        dataStore.edit { it.clear() }
    }

    override val data: Flow<String?> get() = store.map { it.sessionToken }

    override fun mapPreferencesToStore(preferences: Preferences): AuthLocalStorage {
        val token = preferences[PreferenceKeys.TOKEN]

        return AuthLocalStorage(token)
    }

    private object PreferenceKeys {
        val TOKEN = stringPreferencesKey("token")
    }
}
