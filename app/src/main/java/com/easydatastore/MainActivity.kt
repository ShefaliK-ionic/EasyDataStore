package com.easydatastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.easydatastore.databinding.ActivityMainBinding
import com.easydatastore.utils.AppDataStore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    @Inject
lateinit var appDataStore: AppDataStore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        clickEvent()
        observeData()


    }


    private fun observeData() {
        // Updates age
        // every time user age changes it will be observed by userIdFlow
        // here it refers to the value returned from the userIdFlow function
        // of UserManager class
        appDataStore.userIdFlow.asLiveData().observe(this) {
            activityMainBinding.tvUserField.text = it.toString()
        }

        // Updates name
        // every time user name changes it will be observed by userNameFlow
        // here it refers to the value returned from the usernameFlow function
        // of UserManager class
        appDataStore.userNameFlow.asLiveData().observe(this) {
            activityMainBinding.tvUserData.text=it.toString()
        }
    }

    private fun clickEvent() {
        activityMainBinding.btnSave.setOnClickListener {

            lifecycleScope.launch {
                appDataStore.storeUser(activityMainBinding.etAge.text.toString().toInt(), activityMainBinding.etName.text.toString())
            }

        }

    }

//    private val dataStoreN = createDataStore(name = "employee_settings_prefs")






}