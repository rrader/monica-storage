package com.antiglukapps.monikaapp.ui.shoppinglist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoppingListViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Shopping list Fragment"
    }
    val text: LiveData<String> = _text
}
