package com.aidev.kotlinexpert.ui.screens.home

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.aidev.kotlinexpert.data.Note

@Composable
@Preview
fun HomeScreen(vm: HomeViewModel, onNoteClick: (noteId: Long) -> Unit) {
    MaterialTheme {
        Scaffold(
            topBar = { MyCustomTopBar(vm::onFilterClick ) },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { onNoteClick(Note.NEW_NOTE) }
                ) {
                    Icon(imageVector = Icons.Default.Add, "Add Note")
                }
            }
        ) { padding ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize().padding(padding)
            ) {
                if(vm.state.loading) {
                    CircularProgressIndicator()
                }
                vm.state.filteredNotes?.let {listNotes ->
                    MyNotesList(
                        notes = listNotes,
                        onNoteClick = { onNoteClick(it.id) }
                    )
                }
            }
        }
    }
}

