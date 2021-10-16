package com.google.note.cleannote.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.note.cleannote.feature_node.domain.model.Note
import com.google.note.cleannote.feature_node.domain.use_case.DeleteNote
import com.google.note.cleannote.feature_node.domain.use_case.NoteUseCases
import com.google.note.cleannote.feature_node.domain.util.NoteOrder
import com.google.note.cleannote.feature_node.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
): ViewModel(){

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state
    private var recentlyDeleteNote: Note? = null

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    var getNotesJob: Job? = null

    fun onEvent(event: NotesEvent){
        when(event){
            is NotesEvent.Order ->{
                if (state.value.noteOder::class == event.noteOrder.orderType::class &&
                        state.value.noteOder.orderType == event.noteOrder.orderType){
                    return
                }
                getNotes(event.noteOrder)
            }
            is NotesEvent.Delete ->{
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                    recentlyDeleteNote = event.note
                }
            }
            is NotesEvent.RestoreNote ->{
                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeleteNote?: return@launch)
                    recentlyDeleteNote = null
                }
            }
            is NotesEvent.ToggleOrderSection ->{
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder){
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes(noteOrder)
            .onEach { notes->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }

}