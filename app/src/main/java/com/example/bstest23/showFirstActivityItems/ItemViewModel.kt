package com.example.bstest23.showFirstActivityItems

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bstest23.networkCommunication.ModelList

class ItemViewModel : ViewModel() {
    val followingTagDataList: MutableLiveData<MutableList<ModelList>> = MutableLiveData(mutableListOf())
    fun loadFollowingData() {
        ModelList().loadApi { isSuccess, dataList, error ->
            if (isSuccess) {
                dataList?.let {
                    followingTagDataList.postValue(it.toMutableList())
                }
            }
        }
    }
}