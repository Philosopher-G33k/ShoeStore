package com.udacity.shoestore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WelcomeViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    init {
        Log.i("WelcomeViewModel","View model initialized")
    }

    private val _button_clicked = MutableLiveData<Boolean>()
    val button_clicked: LiveData<Boolean>
        get() = _button_clicked

    fun setButtonClickTrue() {
        _button_clicked.value = true
    }

    fun setButtonClickedFalse() {
        _button_clicked.value = false
    }
}