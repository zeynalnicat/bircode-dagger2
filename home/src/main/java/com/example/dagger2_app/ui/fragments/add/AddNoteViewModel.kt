package com.example.dagger2_app.ui.fragments.add


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.extensions.launch
import com.example.dagger2_app.data.local.NoteDao
import com.example.dagger2_app.models.NoteDTO
import com.example.dagger2_app.models.mapToEntity
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddNoteViewModel @Inject constructor(val noteDao: NoteDao, val router: Router) : ViewModel() {

    private val _state = MutableStateFlow(AddNoteState())

    val state: StateFlow<AddNoteState> = _state.asStateFlow()


    private val _effect = MutableSharedFlow<AddNoteUiEffect>()
    val effect = _effect.asSharedFlow()

    fun onIntent(intent: AddNoteIntent) {
        when (intent) {
            is AddNoteIntent.OnAddNote -> addNote()
            AddNoteIntent.OnNavigateBack -> router.exit()
            is AddNoteIntent.OnSetDescription -> _state.update { it.copy(description = intent.description) }
            is AddNoteIntent.OnSetTitle -> _state.update { it.copy(title = intent.title) }
        }
    }

    private fun addNote() {
        var insertion = -1L

        launch(
            onError = { e ->
                _effect.emit(
                    AddNoteUiEffect.ShowSnackbar(
                        e.message ?: "Couldn't insert"
                    )
                )
            },
            onCallMethod = {
                val note = NoteDTO(0, _state.value.title, _state.value.description)
                insertion = noteDao.insert(note.mapToEntity())
            },
            onSuccess = {
                if (insertion!=-1L) {
                   _effect.emit(AddNoteUiEffect.NotifyInsertion)

                } else {
                     _effect.emit(AddNoteUiEffect.ShowSnackbar("Couldn't insert"))


                }
            }
        )
    }

}