package com.google.note.cleannote.presentation.notes

import android.provider.ContactsContract
import com.google.note.cleannote.feature_node.domain.model.Note
import com.google.note.cleannote.feature_node.domain.util.NoteOrder
import com.google.note.cleannote.feature_node.domain.util.OrderType

data class NotesState (
    val notes: List<Note> = emptyList(),
    val noteOder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)