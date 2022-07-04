package com.hilberling.treblleapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hilberling.treblleapp.data.RegisterUser
import com.hilberling.treblleapp.data.Repository
import com.hilberling.treblleapp.data.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val repository: Repository): ViewModel() {
    private val _registerRequestState = MutableStateFlow<Resource<Nothing>?>(null)
    val registerRequestState: StateFlow<Resource<Nothing>?>
    get() = _registerRequestState

    val errorHandler = CoroutineExceptionHandler{_, error ->
        if(error is Exception) {
            _registerRequestState.value = Resource.Error(error.message!!)
        }
    }

    fun registerUser(registerUser: RegisterUser) {
        // once the user is registered we assign loading
        _registerRequestState.value = Resource.Loading(null)
        viewModelScope.launch(Dispatchers.IO + errorHandler) {
            repository.registerUser(registerUser = registerUser)
            // once done loading we assign success
            _registerRequestState.value = Resource.Success(null)
        }
    }
}