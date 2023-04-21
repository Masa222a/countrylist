package com.example.okhttp.view.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.okhttp.model.Entity.Flag
import com.example.okhttp.model.ScrapingManager
import com.example.okhttp.model.XmlManager
import com.example.okhttp.adapter.CountriesDetailEmbassyAdapter
import com.example.okhttp.databinding.FragmentDetailEmbassyBinding
import kotlinx.coroutines.*

class DetailEmbassyFragment : Fragment() {
    lateinit var binding: FragmentDetailEmbassyBinding
    private var adapter: CountriesDetailEmbassyAdapter? = null
    var scrapingManager = ScrapingManager()

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailEmbassyBinding.inflate(inflater, container, false)
        adapter = CountriesDetailEmbassyAdapter()
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(view?.context)
        recyclerView.adapter = adapter
        val flag = arguments?.getSerializable("flag") as Flag
        val url = ScrapingManager.UrlCreate(XmlManager.Region.indexOf(flag.region), flag).mainUrl
        GlobalScope.launch(Dispatchers.Main) {
            changeList(url)
        }
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private suspend fun changeList(url: String) {
        withContext(Dispatchers.Main) {
            val archive = scrapingManager.fetchUrl(url)
            adapter?.embassyList = archive
            adapter?.notifyDataSetChanged()
        }
    }

}