package com.sberkozd.medistock.ui.login

import com.sberkozd.medistock.data.User
import com.sberkozd.medistock.db.MediDao
import javax.inject.Inject

class LoginRepository @Inject constructor(private val mediDao: MediDao) {

    suspend fun getUser(username: String): User? {
        return mediDao.getUserByUsername(username)
    }

}