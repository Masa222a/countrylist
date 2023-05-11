package com.example.countrylist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrylist.model.Entity.Embassy
import com.example.countrylist.model.Entity.Flag
import com.example.countrylist.model.ScrapingManager
import com.example.countrylist.model.XmlManager
import kotlinx.coroutines.launch

class DetailEmbassyFragmentViewModel : ViewModel() {
    var embassyData = MutableLiveData<MutableList<Embassy>>()
    private val scrapingManager = ScrapingManager()

    fun getEmbassyData(flag: Flag) {
        viewModelScope.launch {
            val url = ScrapingManager.UrlCreate(XmlManager.Region.indexOf(flag.region), flag).mainUrl
            embassyData.postValue(scrapingManager.fetchUrl(url))
        }
    }
}