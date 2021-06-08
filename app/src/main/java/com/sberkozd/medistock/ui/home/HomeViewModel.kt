package com.sberkozd.medistock.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sberkozd.medistock.data.Medicine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repository: HomeRepository) : ViewModel() {

    var medicineList: MutableLiveData<List<Medicine>> = MutableLiveData()

    init {
        viewModelScope.launch {
            medicineList.value = repository.getAllMedicines()
        }
    }

    fun updateStockOfMedicine(id: Int, stock: String) {
        viewModelScope.launch {
            repository.updateStockById(id, stock)

            medicineList.value = repository.getAllMedicines()
        }
    }
}