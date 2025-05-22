package com.example.dagger2_app.ui.fragments.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dagger2_app.data.local.NoteDao
import com.example.dagger2_app.models.NoteDTO
import com.example.dagger2_app.models.mapToEntity
import com.example.dagger2_app.resource.DBResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddNoteViewModel @Inject constructor(val noteDao: NoteDao): ViewModel() {

    private val _insertion = MutableLiveData<DBResult<Unit>>()

    val insertion : LiveData<DBResult<Unit>> get() = _insertion
    fun addNote(noteDTO: NoteDTO){
        viewModelScope.launch {
            try {
                if(noteDao.insert(noteDTO.mapToEntity())!=-1L){
                   _insertion.value = DBResult.Success(Unit)
                }
                else {
                    _insertion.value = DBResult.Error("Couldn't insert")
                }
            }catch (e:Exception){
                _insertion.value = DBResult.Error(e.message ?: "Couldn't insert")
            }
        }
    }
}