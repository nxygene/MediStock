package com.sberkozd.medistock.ui.home

import com.sberkozd.medistock.data.Medicine

interface StockUpdateListener {

    fun onUpdate(medicine: Medicine, stock: String)

}
