package com.example.okhttp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.okhttp.model.XmlManager

class DetailVisaFragmentViewModel : ViewModel() {
    var visaData = MutableLiveData<String>()
    var xmlManager = XmlManager()

    fun getVisaData(id: Int) {
        val data = xmlManager.changeVisaList(id)
        data[0].content.replace(" +".toRegex(), "\n")
        visaData.postValue(data[0].content.replace(" +".toRegex(), "\n"))
    }
}