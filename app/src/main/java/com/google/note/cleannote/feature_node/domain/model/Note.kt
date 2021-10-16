package com.google.note.cleannote.feature_node.domain.model

import androidx.compose.material.lightColors
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.note.cleannote.ui.theme.*
import java.lang.Exception

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
){
    companion object{
        val noteColor = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidException(message: String): Exception(message)
