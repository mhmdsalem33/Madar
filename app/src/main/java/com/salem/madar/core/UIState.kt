package com.salem.madar.core



sealed class UiState<T>(
    val data     : T?       = null ,
    val message  : String ? = null,
)
{
    class Idle<T>         : UiState<T>()
    class Loading<T>         : UiState<T>()
    class Success<T>(data : T)       : UiState<T>(data = data)
    class Error<T>(message: String)  : UiState<T>(message = message)
}


