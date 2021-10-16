package com.google.note.cleannote.feature_node.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.google.note.cleannote.feature_node.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase: RoomDatabase(){
    abstract val noteDao: NoteDao

    companion object{
        const val DATABASE_NAME = "notes_db"
    }
}