package com.sberkozd.medistock.di

import android.content.Context
import androidx.room.Room
import com.sberkozd.medistock.db.MediDao
import com.sberkozd.medistock.db.MediDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): MediDatabase {
        return Room.databaseBuilder(context, MediDatabase::class.java, "MediStock.db")
            .allowMainThreadQueries()
            .createFromAsset("database/MediStock.db")
            .build()
    }


    @Provides
    @Singleton
    fun provideMediDao(database: MediDatabase): MediDao {
        return database.mediDao()
    }


}