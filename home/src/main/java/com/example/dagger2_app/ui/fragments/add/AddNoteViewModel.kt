package com.example.dagger2_app.ui.fragments.add


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.constants.AppStrings
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
            is AddNoteIntent.OnAddNote -> addNote(intent.isUpdate)
            AddNoteIntent.OnNavigateBack -> router.exit()
            is AddNoteIntent.OnSetDescription -> _state.update { it.copy(description = intent.description) }
            is AddNoteIntent.OnSetTitle -> _state.update { it.copy(title = intent.title) }
            is AddNoteIntent.OnSetId -> _state.update { it.copy(id =intent.id) }
        }
    }

    private fun addNote(isUpdate: Boolean) {
        var insertion = -1L

        launch(
            onError = { e ->
                _effect.emit(
                    AddNoteUiEffect.ShowSnackbar(
                        e.message ?: AppStrings.insertionError
                    )
                )
            },
            onCallMethod = {
                if(!isUpdate){
                    val note = NoteDTO(0, _state.value.title, _state.value.description)
                    insertion = noteDao.insert(note.mapToEntity())
                }
                else{
                    val note = NoteDTO(_state.value.id, _state.value.title, _state.value.description)
                    insertion = noteDao.update(note.mapToEntity()).toLong()
                }

            },
            onSuccess = {
                if (insertion!=-1L) {
                   _effect.emit(AddNoteUiEffect.NotifyInsertion)

                } else {
                     _effect.emit(AddNoteUiEffect.ShowSnackbar(AppStrings.insertionError))


                }
            }
        )
    }

}