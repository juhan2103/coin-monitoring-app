package com.nunu.coco.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.nunu.coco.App

class MyDataStore {

    private val context = App.context()

    companion object{
        private val Context.dataStore : DataStore<Preferences> by preferencesDataStore("user_pref")
    }

    private val mDataStore : DataStore<Preferences> = context.dataStore

    private val FIRST_FLAG = booleanPreferencesKey("FIRST_FLAG")

    // MainActivity로 갈 때 TRUE로 변경

    // 처음 접속하는 유저가 아니면 -> true
    // 처음 접속하는 유저이면 -> false

    suspend fun setUpFirstData(){
        mDataStore.edit { preference ->
            preference[FIRST_FLAG] = true
        }
    }

    suspend fun getFirstData() : Boolean {

        var currentValue = false

        mDataStore.edit { preference ->
            currentValue = preference[FIRST_FLAG] ?: false
        }

        return currentValue
    }

}