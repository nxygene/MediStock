package com.sberkozd.medistock.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: LoginRepository) : ViewModel() {

    var toastString = MutableLiveData<String>()

    var loggedInUserId = MutableLiveData<Int>()

    var isUserAdmin = false

    fun loginClicked(username: String, password: String) {
        viewModelScope.launch {
            val user = repository.getUser(username)
            if (user == null) {
                toastString.value = "User not found!"
            } else {
                if (user.password == password) {
                    isUserAdmin = user.role == "Admin"
                    toastString.value = "Welcome $username - ${user.role}"
                    loggedInUserId.value = user.id
                } else {
                    toastString.value = "Wrong password!"
                }
            }
        }

    }
}