package com.example.okhttp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.okhttp.model.Entity.Embassy
import com.example.okhttp.model.Entity.Flag
import com.example.okhttp.model.ScrapingManager
import com.example.okhttp.model.XmlManager
import kotlinx.coroutines.launch

class DetailEmbassyFragmentViewModel : ViewModel() {
    var visaData = MutableLiveData<MutableList<Embassy>>()
    private val scrapingManager = ScrapingManager()

    fun getEmbassyData(flag: Flag) {
        viewModelScope.launch {
            val url = ScrapingManager.UrlCreate(XmlManager.Region.indexOf(flag.region), flag).mainUrl
            visaData.postValue(scrapingManager.fetchUrl(url))
        }
    }
}