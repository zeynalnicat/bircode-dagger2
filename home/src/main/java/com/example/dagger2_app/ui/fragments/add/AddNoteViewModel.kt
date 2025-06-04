package com.example.dagger2_app.ui.fragments.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dagger2_app.data.local.NoteDao
import com.example.dagger2_app.models.NoteDTO
import com.example.dagger2_app.models.mapToEntity
import com.example.dagger2_app.resource.DBResult
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddNoteViewModel @Inject constructor(val noteDao: NoteDao, val router: Router): ViewModel() {

    private val _state = MutableStateFlow(AddNoteState())

    val state: StateFlow<AddNoteState> = _state.asStateFlow()

    fun onIntent(intent: AddNoteIntent){
        when(intent){
            is AddNoteIntent.OnAddNote -> addNote(intent.note)
            AddNoteIntent.OnClearState -> clearState()
            AddNoteIntent.OnNavigateBack -> router.exit()
        }
    }

    private fun addNote(noteDTO: NoteDTO){
        viewModelScope.launch {
            try {
                if(noteDao.insert(noteDTO.mapToEntity())!=-1L){
                    _state.update { it.copy(insertion = true) }
                }
                else {
                    _state.update { it.copy(error = "Couldn't insert") }
                }
            }catch (e:Exception){
                _state.update{it.copy(error = e.message ?: "Couldn't insert")}
            }
        }
    }

    private fun clearState(){
        _state.update { it.copy(false,"") }
    }
}