package com.example.core.extensions

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


fun ViewModel.launch(onError: suspend (e: Exception)->Unit, onSuccess: suspend ()->Unit, onFinally:()->Unit={}){
    CoroutineScope(Dispatchers.IO).launch {
        try {
            onSuccess()
        } catch (e: Exception) {
            onError(e)
        } finally {
            onFinally()
        }
    }
}
