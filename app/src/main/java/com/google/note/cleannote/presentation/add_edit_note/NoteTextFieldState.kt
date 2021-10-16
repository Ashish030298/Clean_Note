package com.google.note.cleannote.presentation.add_edit_note

data class NoteTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHIntVisible: Boolean = true
    )