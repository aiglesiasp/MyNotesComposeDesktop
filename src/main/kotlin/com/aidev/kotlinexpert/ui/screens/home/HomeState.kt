package com.aidev.kotlinexpert.ui.screens.home

import androidx.compose.runtime.mutableStateOf
import com.aidev.kotlinexpert.data.Filter
import com.aidev.kotlinexpert.data.Note
import com.aidev.kotlinexpert.data.fakeNotes
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



object HomeState {
    private val _states = MutableStateFlow(UiState())
    val states = _states.asStateFlow()

    //var states: UiState by MutableStateFlow(UiState())
    //    private set

    fun loadNotes(coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            _states.value = UiState(loading = true)
            Note.fakeNotes.collect {
                _states.value = UiState(notes = it, loading = false)
            }

        }
    }

    fun onFilterClick(filter: Filter) {
        _states.update { it.copy(filter = filter) }
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
