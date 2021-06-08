package com.sberkozd.medistock.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sberkozd.medistock.data.Medicine
import com.sberkozd.medistock.data.User

@Dao
interface MediDao {

    @Query("SELECT * FROM user")
    fun readAllData(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user WHERE username = :username")
    suspend fun getUserByUsername(username: String): User?

    @Query("SELECT * FROM medicine")
    suspend fun getAllMedicines(): List<Medicine>

    @Query("UPDATE medicine SET stock = :stock where id = :id")
    suspend fun updateStockById(id: Int, stock: String)

}