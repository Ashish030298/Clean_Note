package com.google.note.cleannote.feature_node.domain.use_case

data class NoteUseCases(
    val getNotes: GetNotes,
    val deleteNote:DeleteNote,
    val addNote: AddNote,
    val getNote: GetNote
)
