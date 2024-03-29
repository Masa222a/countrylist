package jp.search.countrylist.view.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import jp.search.countrylist.adapter.CountriesListAdapter
import jp.search.countrylist.model.Entity.Flag
import jp.search.countrylist.model.XmlManager
import jp.search.countrylist.viewmodel.MainFragmentViewModel
import jp.search.okhttp.R
import jp.search.okhttp.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private var adapter: CountriesListAdapter? = null
    private var flagList = listOf<Flag>()
    private val viewModel: MainFragmentViewModel by viewModels()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)

        setupObserve()

        adapter = CountriesListAdapter(flagList)
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(view?.context)
            recyclerView.adapter = adapter

            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if (tab != null) {
                        viewModel.getFlagList(XmlManager.Region.indexOf(tab.position))
                        adapter?.flagList = viewModel.flagList.value!!
                        viewModel.tabPosition.postValue(tab.position)

                        recyclerView.scrollToPosition(0)
                        adapter?.notifyDataSetChanged()
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    recyclerView.scrollToPosition(0)
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    recyclerView.scrollToPosition(0)
                }

            })
        }

        adapter!!.setOnCountryClickListener(
            object : CountriesListAdapter.OnCountryCellClickListener {
                override fun onItemClick(flag: Flag) {
                    val fragment = DetailFragment()
                    val bundle = Bundle()
                    bundle.putSerializable("country", flag)
                    fragment.arguments = bundle
                    parentFragmentManager
                        .beginTransaction()
                        .replace(R.id.container, fragment)
                        .addToBackStack(null)
                        .commit()
                }
            }
        )

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        viewModel.tabPosition.observe(viewLifecycleOwner) {
            binding.tabLayout.getTabAt(it)?.select()
        }
    }

    private fun setupObserve() {
        viewModel.getFlagList(XmlManager.Region.Asia)

        viewModel.flagList.observe(viewLifecycleOwner) {
            adapter?.flagList = it
        }
    }
}