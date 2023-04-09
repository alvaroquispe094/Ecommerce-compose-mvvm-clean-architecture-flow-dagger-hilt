package com.groupal.shared.ecommerce.data.local.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.groupal.shared.ecommerce.data.json.getJsonAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

interface ILocalRepository<Store> {
    val dataStore: DataStore<androidx.datastore.preferences.core.Preferences>

    fun mapPreferencesToStore(preferences: Preferences): Store
}

val <Store> ILocalRepository<Store>.store: Flow<Store> get() = dataStore.data.map { preferences ->
    mapPreferencesToStore(preferences)
}

suspend inline fun <reified T> ILocalRepository<*>.setValue(
    key: Preferences.Key<String>,
    value: T
) {
    dataStore.edit {
        it[key] = getJsonAdapter(T::class.java).toJson(value)
    }
}

suspend fun ILocalRepository<*>.clearBy(flow: Flow<Boolean>) {
    flow.filter { it }.collect {
        dataStore.edit { it.clear() }
    }
}

fun <T> parseLocalRepositoryPreference(
    preferences: Preferences,
    preference: Preferences.Key<String>,
    klass: Class<T>
): T? = preferences[preference]?.let { getJsonAdapter(klass).fromJson(it) }
