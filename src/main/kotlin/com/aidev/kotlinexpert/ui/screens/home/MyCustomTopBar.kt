package com.aidev.kotlinexpert.ui.screens.home

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.runtime.*
import com.aidev.kotlinexpert.data.Filter
import com.aidev.kotlinexpert.data.Note

@Composable
fun MyCustomTopBar(onFilterClick: (Filter) -> Unit) {
    TopAppBar(title = { Text("MY NOTES") },
        actions = {
            FiltersAction(onFilterClick)
        }
    )
}

@Composable
private fun FiltersAction(onFilterClick: (Filter) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Default.FilterList,
            contentDescription = "Filter"
        )

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {

            listOf(
                Filter.All to "All",
                Filter.ByType(Note.NoteType.TEXT) to "Text",
                Filter.ByType(Note.NoteType.AUDIO) to "Audio"
            ).forEach { (filter, label) ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    onFilterClick(filter)
                }) {
                    Text(label)
                }
            }
        }
    }
}