package com.example.dagger2_app.resource



sealed class DBResult<T> {
    data class Success<T>(val data:T):DBResult<T>()
    data class Error<Nothing>(val message:String):DBResult<Nothing>()
//    data object Loading: DBResult<Nothing>()


}