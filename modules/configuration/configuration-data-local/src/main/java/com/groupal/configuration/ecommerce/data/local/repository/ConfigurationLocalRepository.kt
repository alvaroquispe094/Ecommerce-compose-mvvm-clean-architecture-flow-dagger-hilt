/*
package com.groupal.configuration.ecommerce.data.local.repository

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.groupal.configuration.ecommerce.data.IConfigurationLocalRepository
import com.groupal.configuration.ecommerce.domain.FeatureFlags
import com.groupal.configuration.ecommerce.domain.Token
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class ConfigurationLocalRepository @Inject constructor(
    @ApplicationContext private val context: Context
) : IConfigurationLocalRepository {

    */
/*companion object {
        private const val APP_CONFIGURATIONS = "app_configuration"
    }*//*


  */
/*  private val Context.configurationDataStore by preferencesDataStore(
        name = APP_CONFIGURATIONS
    )*//*


    private object ConfigurationPreferenceKeys {
        val SIGN_IN_ENABLED = booleanPreferencesKey("sign_in_enabled")
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
    }

    */
/*override val configuration: Flow<FeatureFlags> = context.configurationDataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                //Log.e(TAG, "Error reading preferences.", exception)
                emit(emptyPreferences())
            } else {
                throw exception // TODO:
            }
        }.map { preferences ->
            mapConfigurationDataStore(preferences)
        }

    override val configurationToken: Flow<Token> = context.configurationDataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                //Log.e(TAG, "Error reading preferences.", exception)
                emit(emptyPreferences())
            } else {
                throw exception // TODO:
            }
        }.map { preferences ->
            mapConfigurationTokenDataStore(preferences)
        }*//*


    private fun mapConfigurationDataStore(preferences: Preferences): FeatureFlags =
        FeatureFlags(preferences[ConfigurationPreferenceKeys.SIGN_IN_ENABLED] ?: false)

    private fun mapConfigurationTokenDataStore(preferences: Preferences): Token =
        Token(preferences[ConfigurationPreferenceKeys.ACCESS_TOKEN] ?: "")

    */
/*override suspend fun update(featureFlags: FeatureFlags) {
        context.configurationDataStore.edit { it[ConfigurationPreferenceKeys.SIGN_IN_ENABLED] = featureFlags.isSignUpEnable }
    }

    override suspend fun updateToken(token: Token) {
        context.configurationDataStore.edit { it[ConfigurationPreferenceKeys.ACCESS_TOKEN] = token.accessToken }
    }*//*

}
*/
