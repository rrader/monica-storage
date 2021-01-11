package com.antiglukapps.monikaapp.ui.storageItemEdit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddStorageItemViewModel : ViewModel() {
    val name = MutableLiveData<String>().apply {
        value = "Item Name"
    }

    val expirationDate = MutableLiveData<Long>().apply {
        value = 0
    }
}
