package com.salem.madar.presentation.ui.xml.fragments.show_users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salem.madar.domain.models.User
import com.salem.madar.domain.usecase.GetSavedUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowUsersFragmentViewModel @Inject constructor(
    private val getSavedUsersUseCase: GetSavedUsersUseCase
) : ViewModel() {


    private val _usersState = MutableStateFlow<List<User>>(emptyList())
    val usersState = _usersState.asStateFlow()


    init {
        getSavedUsers()
    }

    private fun getSavedUsers() = viewModelScope.launch {
        getSavedUsersUseCase().collect {
            _usersState.emit(it)
        }
    }


}