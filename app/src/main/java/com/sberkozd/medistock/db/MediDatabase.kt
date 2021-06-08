package com.sberkozd.medistock.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sberkozd.medistock.data.Medicine
import com.sberkozd.medistock.data.User

@Database(
    entities = [User::class, Medicine::class],
    version = 1
)

abstract class MediDatabase : RoomDatabase() {

    abstract fun mediDao(): MediDao

}