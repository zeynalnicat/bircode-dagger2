package com.example.dagger2_app.models

data class NoteDTO(
    val id: Int,
    val title: String,
    val description: String
)


fun NoteDTO.mapToEntity(): NoteEntity {
    return NoteEntity(this.id, this.title, this.description)
}
