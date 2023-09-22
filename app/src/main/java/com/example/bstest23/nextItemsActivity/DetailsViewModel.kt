package com.example.bstest23.nextItemsActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailsViewModel : ViewModel() {
    val dataList: MutableLiveData<MutableList<String>> = MutableLiveData(mutableListOf())

}