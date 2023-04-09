package com.groupal.configuration.ecommerce.data.local.repository

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.groupal.configuration.ecommerce.data.IConfigurationLocalRepository
import com.groupal.configuration.ecommerce.domain.ConfigurationStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class ConfigurationLocalRepository(private val context: Context) : IConfigurationLocalRepository {

    companion object {
        private const val APP_CONFIGURATIONS = "app_configuration"
    }

    private val Context.configurationDataStore by preferencesDataStore(
        name = APP_CONFIGURATIONS
    )

    private object ConfigurationPreferenceKeys {
        val STAGE = intPreferencesKey("onboardingStage")
    }

    override val configuration: Flow<ConfigurationStorage> = context.configurationDataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            }
        }.map { preferences ->
            mapConfigurationDataStore(preferences)
        }

    override suspend fun updateOnBoardingStage(onBoardingStage: Int) {
        context.configurationDataStore.edit { it[ConfigurationPreferenceKeys.STAGE] = onBoardingStage }
    }

    override suspend fun clear() {
        context.configurationDataStore.edit { it.clear() }
    }

    private fun mapConfigurationDataStore(preferences: Preferences): ConfigurationStorage {
        val onBoardingStage = preferences[ConfigurationPreferenceKeys.STAGE]

        return ConfigurationStorage(onBoardingStage = onBoardingStage)
    }
}