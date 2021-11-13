package com.udacity.shoestore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    private val _popStack = MutableLiveData<Boolean>()
    val popStack: LiveData<Boolean>
    get() = _popStack


    init {
        Log.i("ShoeListViewModel", "ShoeListViewModel initialized")
        _shoeList.value = arrayListOf()

    }

    fun addShoe(shoe: Shoe) {
        var shoeListTemp = _shoeList.value
        shoeListTemp?.add(shoe)
        _shoeList.value = shoeListTemp?: arrayListOf()
    }

    fun setPopStackTrue(){
        _popStack.value = true
    }

    fun  setPopStackFalse(){
        _popStack.value = false
    }
}