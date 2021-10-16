package com.google.note.cleannote.presentation.util

sealed class Screen(val route: String){
    object NoteScreen: Screen("notes_screen")
    object AddEditNoteScreen: Screen("add_edit_note_screen")
}
