package com.google.note.cleannote.feature_node.data.data_source

import androidx.room.*
import com.google.note.cleannote.feature_node.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
sealed interface NoteDao{
    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>

    @Query(
        "SELECT * FROM  note WHERE id = :id"
    )
    suspend fun getNoteById(id: Int):Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

}