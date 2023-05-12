package jp.search.countrylist.view.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import jp.search.countrylist.adapter.CountriesDetailEmbassyAdapter
import jp.search.countrylist.model.Entity.Embassy
import jp.search.countrylist.model.Entity.Flag
import jp.search.countrylist.model.ScrapingManager
import jp.search.countrylist.viewmodel.DetailEmbassyFragmentViewModel
import jp.search.okhttp.databinding.FragmentDetailEmbassyBinding
import kotlinx.coroutines.*

class DetailEmbassyFragment : Fragment() {
    lateinit var binding: FragmentDetailEmbassyBinding
    private var adapter: CountriesDetailEmbassyAdapter? = null
    var scrapingManager = ScrapingManager()
    private val viewModel: DetailEmbassyFragmentViewModel by viewModels()

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
        viewModel.getEmbassyData(flag)

        viewModel.embassyData.observe(viewLifecycleOwner) {
            GlobalScope.launch(Dispatchers.Main) {
                changeList(it)
            }
        }
        
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private suspend fun changeList(archive: MutableList<Embassy>) {
        withContext(Dispatchers.Main) {
            adapter?.embassyList = archive
            adapter?.notifyDataSetChanged()
        }
    }

}