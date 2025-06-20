package com.example.core.extensions

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


fun ViewModel.launch(
    onFinally: () -> Unit = {},
    onError: suspend (e: Exception) -> Unit = {},
    onCallMethod: suspend () -> Unit,
) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            onCallMethod()
        } catch (e: Exception) {
            onError(e)
        } finally {
            onFinally()
        }

    }

}
