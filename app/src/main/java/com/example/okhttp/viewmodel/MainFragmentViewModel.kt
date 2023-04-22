package com.example.okhttp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.okhttp.model.Entity.Flag
import com.example.okhttp.model.XmlManager

class MainFragmentViewModel : ViewModel() {
    var flagList = MutableLiveData(listOf<Flag>())
    var xmlManager = XmlManager()

    fun getFlagList(region: XmlManager.Region) {
        flagList.postValue(xmlManager.changeCountriesList(region))
    }
}