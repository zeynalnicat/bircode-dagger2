package com.example.core.extensions

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


fun ViewModel.launch(
    onError: suspend (e: Exception) -> Unit,
    onCallMethod: suspend () -> Unit,
    onSuccess: suspend ()->Unit,
    onFinally: () -> Unit = {}
) {
    var flag = 0
    CoroutineScope(Dispatchers.IO).launch {
        try {
            onCallMethod()
            flag = 1
        } catch (e: Exception) {
            onError(e)
            flag = 0
        } finally {
            onFinally()
        }

        if(flag==1){
            onSuccess()
        }
    }


}
