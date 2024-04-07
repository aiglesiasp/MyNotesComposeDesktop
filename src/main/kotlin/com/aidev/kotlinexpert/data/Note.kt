package com.aidev.kotlinexpert.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

data class Note(
    val title: String,
    val description: String,
    val type: NoteType = NoteType.AUDIO
) {
    enum class NoteType { TEXT, AUDIO}
    companion object
}

val Note.Companion.fakeNotes get() = flow {
   delay(2000)
    val list = (1..10).map {
       Note(
            title = "Title $it",
            description = "Description $it",
            type = if(it % 3 == 0) Note.NoteType.AUDIO else Note.NoteType.TEXT
        )
    }
    emit(list)
}

fun test() {
    val note = Note("1","1", type = Note.NoteType.AUDIO)
    Note.fakeNotes
}

