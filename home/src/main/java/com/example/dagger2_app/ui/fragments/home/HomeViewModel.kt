package com.example.dagger2_app.ui.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dagger2_app.data.local.NoteDao
import com.example.dagger2_app.models.NoteDTO
import com.example.dagger2_app.resource.DBResult
import com.example.dagger2_app.utils.extension.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val noteDao: NoteDao):ViewModel() {
    private val _notes = MutableLiveData<DBResult<List<NoteDTO>>>()

    val notes : LiveData<DBResult<List<NoteDTO>>> get() = _notes

    fun getNotes(){
        viewModelScope.launch() {
            try {
                val noteResult = noteDao.getNotes()
                _notes.value = DBResult.Success(noteResult.map { it.map() })
            }catch (e:Exception){
               _notes.value = DBResult.Error(e.message ?: "Unknown error")
            }
        }
    }
}