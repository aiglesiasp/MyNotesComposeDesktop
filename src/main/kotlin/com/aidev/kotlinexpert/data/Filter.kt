package com.aidev.kotlinexpert.data

sealed interface Filter {
    data object All : Filter
    class ByType(val type: Note.NoteType) : Filter
}