package com.aidev.kotlinexpert.data

import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val id: Long = NEW_NOTE,
    val title: String,
    val description: String,
    val type: NoteType = NoteType.AUDIO
) {
    companion object {
        const val NEW_NOTE = -1L
    }
    enum class NoteType { TEXT, AUDIO}
}


