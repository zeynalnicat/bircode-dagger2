package com.example.dagger2_app.ui.fragments.add


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.constants.AppStrings
import com.example.core.extensions.launch
import com.example.dagger2_app.data.local.NoteDao
import com.example.dagger2_app.models.NoteDTO

import com.example.dagger2_app.utils.extension.mapToEntity
import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow


class AddNoteViewModel @AssistedInject constructor(val noteDao: NoteDao, val router: Router, @Assisted val noteDTO: NoteDTO) : ViewModel() {

    private val _state = MutableStateFlow(AddNoteState(noteDTO.id,noteDTO.title,noteDTO.description))

    val state: StateFlow<AddNoteState> = _state.asStateFlow()


    private val _effect = MutableSharedFlow<AddNoteUiEffect>()
    val effect = _effect.asSharedFlow()

    fun onIntent(intent: AddNoteIntent) {
        when (intent) {
            is AddNoteIntent.OnAddNote -> addNote(intent.title,intent.description)
            AddNoteIntent.OnNavigateBack -> router.exit()
        }
    }

    private fun addNote(title:String, description:String) {
        var insertion = -1L

        launch(
            onError = ::handleError
        )
        {
                if(noteDTO.id==-1){
                    val note = NoteDTO(0, title, description)
                    insertion = noteDao.insert(note.mapToEntity())
                }
                else{
                    val note = NoteDTO(noteDTO.id,title, description)
                    insertion = noteDao.update(note.mapToEntity()).toLong()
                }

                if (insertion!=-1L) {
                    _effect.emit(AddNoteUiEffect.NotifyInsertion)

                } else {
                    _effect.emit(AddNoteUiEffect.ShowSnackbar(AppStrings.insertionError))


                }
            }


    }

    private suspend  fun handleError(e: Exception){
        _effect.emit(
            AddNoteUiEffect.ShowSnackbar(
                e.message ?: AppStrings.unknownError
            )
        )

    }



}