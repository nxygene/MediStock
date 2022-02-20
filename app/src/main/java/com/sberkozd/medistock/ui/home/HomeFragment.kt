package com.sberkozd.medistock.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sberkozd.medistock.R
import com.sberkozd.medistock.adapter.MedicineListAdapter
import com.sberkozd.medistock.data.Medicine
import com.sberkozd.medistock.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), StockUpdateListener {

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var mediAdapter = MedicineListAdapter(this, requireArguments()["isAdmin"] as Boolean)
        binding.recyclerView.adapter = mediAdapter

        binding.search.addTextChangedListener { text ->
            if (!text.isNullOrEmpty() && text.count() > 2) {
                val filterResult = homeViewModel.medicineList.value?.filter {
                    it.name.lowercase().contains(text.toString().lowercase())
                }
                if (filterResult.isNullOrEmpty()) {
                    binding.fragmentHomeTvNoResult.text = "Sorry, Medicine not found!"
                    binding.fragmentHomeTvNoResult.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                } else {
                    binding.fragmentHomeTvNoResult.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    (binding.recyclerView.adapter as MedicineListAdapter).setItems(
                        filterResult
                    )
                }
            } else {
                binding.recyclerView.visibility = View.VISIBLE
                binding.fragmentHomeTvNoResult.visibility = View.GONE
                binding.search.visibility = View.VISIBLE
                (binding.recyclerView.adapter as MedicineListAdapter).setItems(homeViewModel.medicineList.value!!)
            }
        }

        homeViewModel.medicineList.observe(viewLifecycleOwner) {
            mediAdapter.setItems(it)
        }

    }

    override fun onUpdate(medicine: Medicine, stock: String) {
        homeViewModel.updateStockOfMedicine(medicine.id, stock)
    }
}
