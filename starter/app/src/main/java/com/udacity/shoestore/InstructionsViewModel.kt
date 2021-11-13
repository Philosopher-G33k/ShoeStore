package com.udacity.shoestore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InstructionsViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    init {
        Log.i("InstructionsViewModel", "InstructionsViewModel initialized")
    }

    private val _button_pressed = MutableLiveData<Boolean>()
    val button_pressed: LiveData<Boolean>
        get() = _button_pressed

    fun setButtonPressedTrue() {
        _button_pressed.value = true
    }

    fun setButtonPressedFalse() {
        _button_pressed.value = false
    }
}