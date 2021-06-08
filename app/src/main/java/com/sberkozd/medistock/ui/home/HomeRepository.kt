package com.sberkozd.medistock.ui.home

import com.sberkozd.medistock.data.Medicine
import com.sberkozd.medistock.data.User
import com.sberkozd.medistock.db.MediDao
import javax.inject.Inject

class HomeRepository @Inject constructor(private val mediDao: MediDao) {

    suspend fun getAllMedicines(): List<Medicine> {
        return mediDao.getAllMedicines()
    }

    suspend fun updateStockById(id: Int, stock: String) {
        mediDao.updateStockById(id,stock)
    }
}