package com.udacity.shoestore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    init {
        Log.i("LoginViewModel","LoginViewModel initialized")
    }

    val _buttonPressed = MutableLiveData<Boolean>()
    val buttonPressed: LiveData<Boolean>
        get() = _buttonPressed

    fun setButtonPressedTrue() {
        _buttonPressed.value = true
    }

    fun setButtonPressedFalse(){
        _buttonPressed.value = false
    }
}