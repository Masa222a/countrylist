package jp.search.countrylist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jp.search.countrylist.model.XmlManager

class DetailVisaFragmentViewModel : ViewModel() {
    var visaData = MutableLiveData<String>()
    private val xmlManager = XmlManager()

    fun getVisaData(id: Int) {
        val data = xmlManager.changeVisaList(id)
        visaData.postValue(data[0].content.replace(" +".toRegex(), "\n"))
    }
}