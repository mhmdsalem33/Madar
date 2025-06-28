package com.salem.madar.presentation.ui.xml.fragments.add_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salem.madar.core.UiState
import com.salem.madar.domain.models.GenderModel
import com.salem.madar.domain.models.User
import com.salem.madar.domain.usecase.GetGenderListUseCase
import com.salem.madar.domain.usecase.UpsertUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUserFragmentViewModel @Inject constructor(
    private val upsertUserUseCase: UpsertUserUseCase,
    private val getGenderListUseCase: GetGenderListUseCase
) : ViewModel() {


    private val _upsertUserResult = MutableSharedFlow<UiState<Boolean>>()
    val upsertUserResult = _upsertUserResult.asSharedFlow()


    private var upsertUserJob: Job? = null

    fun upsertUser(user: User) {
        upsertUserJob?.cancel()
        upsertUserJob = viewModelScope.launch(Dispatchers.IO) {
            _upsertUserResult.emit(UiState.Loading())
            try {
                 val result = upsertUserUseCase(user)
                _upsertUserResult.emit(UiState.Success(result))
            } catch (e: Exception) {
                _upsertUserResult.emit(UiState.Error(e.message ?: "Unexpected error"))
            }
        }
    }


    private val _genders = MutableStateFlow<List<GenderModel>>(emptyList())
    val genders = _genders.asStateFlow()


    init {
        getGendersFromJson()
    }

    fun getGendersFromJson() = viewModelScope.launch {
        val genderList =  getGenderListUseCase()
        _genders.emit(genderList)
    }


    private val _selectedGender = MutableStateFlow<GenderModel?>(null)
    val selectedGender = _selectedGender.asStateFlow()

    fun onSelectedGender(gender: GenderModel) = viewModelScope.launch {
        _selectedGender.emit(gender)
    }


    fun clearSelectedGender() {
        _selectedGender.value = null
    }


    override fun onCleared() {
        super.onCleared()
        upsertUserJob?.cancel()
    }

}


