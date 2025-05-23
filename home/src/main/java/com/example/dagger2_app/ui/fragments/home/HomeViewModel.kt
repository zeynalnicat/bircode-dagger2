package com.example.dagger2_app.ui.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dagger2_app.data.local.NoteDao
import com.example.dagger2_app.models.NoteDTO
import com.example.dagger2_app.models.mapToEntity
import com.example.dagger2_app.utils.extension.map
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val noteDao: NoteDao):ViewModel() {

    private val _state = MutableStateFlow(HomeState())

    val state: StateFlow<HomeState> = _state.asStateFlow()

    fun onIntent(intent: HomeIntent){
        when(intent){
            HomeIntent.OnGetDto -> getNotes()
            is HomeIntent.OnRemoveNote -> removeNote(intent.note)
        }
    }

    private fun getNotes(){
        viewModelScope.launch {
            try {
                val noteResult = noteDao.getNotes()
                _state.update {it.copy(notes = noteResult.map { it.map() })}
            }catch (e:Exception){
               _state.update {it.copy(error = e.message ?: "Unknown error" )}
            }
        }
    }

    private fun removeNote(note: NoteDTO){
        viewModelScope.launch {
            try {
                noteDao.remove(note.mapToEntity())
                getNotes()
            }catch (e:Exception){
                _state.update {it.copy(error = e.message ?: "Unknown error" )}
            }
        }
    }
}