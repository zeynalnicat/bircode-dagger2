package com.example.dagger2_app.ui.fragments.home

import android.content.res.TypedArray
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.extensions.launch
import com.example.dagger2_app.HomeNavigator
import com.example.dagger2_app.data.local.NoteDao
import com.example.dagger2_app.models.NoteDTO
import com.example.dagger2_app.models.mapToEntity
import com.example.dagger2_app.utils.extension.map
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val noteDao: NoteDao, private val router: Router) :
    ViewModel() {

//    flows channel state shared cold hot  shared inner params
    //  tryEmit , emit
    // sideEffect errors.
    // AssistedInject   (edit sehifesi paramatrlerle)
    // Viewmodel ext. launch  .

    private val _state = MutableStateFlow(HomeState())

    val state: StateFlow<HomeState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<HomeUiEffect>()
    val effect = _effect.asSharedFlow()

    fun onIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.OnGetDto -> getNotes()
            is HomeIntent.OnRemoveNote -> removeNote(intent.note)
            HomeIntent.OnFinishChain -> router.finishChain()
            is HomeIntent.OnNavigateToAddNoteFragment -> router.navigateTo(
                HomeNavigator.AddNotesFragmentScreen(
                    intent.title,
                    intent.description
                )
            )
        }
    }

    private fun getNotes() {

        launch(
            onError = { e ->
                _effect.emit(HomeUiEffect.ShowSnackbar(e.message ?: "Unknown Error"))
            },
            onSuccess = {
                val noteResult = noteDao.getNotes()
                _state.update { it.copy(notes = noteResult.map { it.map() }) }
            }
        )

    }

    private fun removeNote(note: NoteDTO) {

        launch(
            onError = { e ->
                _effect.emit(
                    HomeUiEffect.ShowSnackbar(
                        e.message ?: "Unknown Error"
                    )
                )
            },
            onSuccess = {
                noteDao.remove(note.mapToEntity())
                val modifiedList = _state.value.notes.toMutableList()
                modifiedList.remove(note)
                _state.update { it.copy(notes = modifiedList.toList()) }
            }
        )
    }
}