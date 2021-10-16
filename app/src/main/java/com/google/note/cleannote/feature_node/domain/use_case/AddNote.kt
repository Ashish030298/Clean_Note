package com.google.note.cleannote.feature_node.domain.use_case

import com.google.note.cleannote.feature_node.domain.model.InvalidException
import com.google.note.cleannote.feature_node.domain.model.Note
import com.google.note.cleannote.feature_node.domain.repository.NoteRepository

class AddNote (
    private val repository: NoteRepository
){
    @Throws(InvalidException::class)
    suspend operator fun invoke(note: Note){
        if (note.title.isBlank()){
            throw InvalidException("The title of the note can't be empty")
        }
        if (note.content.isBlank()){
            throw InvalidException("The content of the note can't be empty")
        }
        repository.insertNote(note)
    }
}