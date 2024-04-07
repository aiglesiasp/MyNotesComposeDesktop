package com.aidev.kotlinexpert.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.aidev.kotlinexpert.data.Filter
import com.aidev.kotlinexpert.data.Note
import com.aidev.kotlinexpert.data.remote.NotesRepository
import com.aidev.kotlinexpert.data.remote.notesClient
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.reflect.KProperty

operator fun <T> StateFlow<T>.getValue(owner: Any?, property: KProperty<*>): T = this.value
operator fun <T> MutableStateFlow<T>.setValue(owner: Any?, property: KProperty<*>, newValue: T) {
    this.value = newValue
}

class HomeViewModel(private val scope: CoroutineScope) {

    var state by mutableStateOf(UiState())
        private set

    init {
        loadNotes()
    }

    private fun loadNotes() {
        scope.launch {
            state = UiState(loading = true)
            val response = NotesRepository.getAll()
            state = UiState(notes = response)
        }
    }

    fun onFilterClick(filter: Filter) {
        state = state.copy(filter = filter)
    }
}

data class UiState(
    val notes: List<Note>? = null,
    val loading: Boolean = false,
    val filter: Filter = Filter.All
) {
    val filteredNotes: List<Note>?
        get() = when(filter) {
            Filter.All -> notes
            is Filter.ByType -> notes?.filter { it.type == filter.type }
        }
}


class AppState1 {
    val text = mutableStateOf("")
    val buttonEnabled: Boolean
        get() = text.value.isNotEmpty()
}
