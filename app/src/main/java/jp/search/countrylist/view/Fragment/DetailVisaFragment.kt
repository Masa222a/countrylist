package jp.search.countrylist.view.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import jp.search.countrylist.model.Entity.Flag
import jp.search.countrylist.viewmodel.DetailVisaFragmentViewModel
import jp.search.okhttp.databinding.FragmentDetailVisaBinding

class DetailVisaFragment : Fragment() {
    lateinit var binding: FragmentDetailVisaBinding
    private val viewModel: DetailVisaFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailVisaBinding.inflate(inflater, container, false)
        val flag = arguments?.getSerializable("flag") as Flag
        viewModel.getVisaData(flag.id)

        viewModel.visaData.observe(viewLifecycleOwner) {
            binding.visaInfomation.text = it
        }

        return binding.root
    }

}