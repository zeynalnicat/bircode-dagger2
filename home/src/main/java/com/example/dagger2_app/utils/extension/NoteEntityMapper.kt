package com.example.dagger2_app.utils.extension

import com.example.dagger2_app.models.NoteDTO
import com.example.dagger2_app.models.NoteEntity

fun NoteEntity.mapToDTO(): NoteDTO {
    return NoteDTO(this.id, this.title, this.description)
}

fun NoteDTO.mapToEntity(): NoteEntity {
    return NoteEntity(this.id, this.title, this.description)
}
