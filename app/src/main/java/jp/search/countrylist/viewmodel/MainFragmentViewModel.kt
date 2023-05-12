package jp.search.countrylist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jp.search.countrylist.model.Entity.Flag
import jp.search.countrylist.model.XmlManager

class MainFragmentViewModel : ViewModel() {
    var flagList = MutableLiveData(listOf<Flag>())
    private val xmlManager = XmlManager()
    var tabPosition = MutableLiveData<Int>()

    fun getFlagList(region: XmlManager.Region) {
        flagList.postValue(xmlManager.changeCountriesList(region))
    }
}