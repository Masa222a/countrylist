package jp.search.countrylist.model

import android.widget.ImageView
import jp.search.countrylist.SingletonContext

class DetailManager {
    private val context = SingletonContext.applicationContext()!!
    var item: String = ""

    fun setPhoto(imageView: ImageView, engName: String?) {
        item = engName + "img"
        val pictureId = context.resources.getIdentifier(item,"drawable", context.packageName)
        imageView.setImageResource(pictureId)
    }
}