package com.sberkozd.medistock.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * This is where we define a medicine's parameters*/

@Entity
data class Medicine(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val stock: Int,
    val image: String?
) {
}