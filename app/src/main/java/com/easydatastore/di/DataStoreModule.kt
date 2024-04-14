package com.easydatastore.di

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

@Provides
fun provideDataStoreObj(@ApplicationContext ctx:Context): DataStore<Preferences> {
    return ctx.createDataStore(name = "employee_prefs")

}

}