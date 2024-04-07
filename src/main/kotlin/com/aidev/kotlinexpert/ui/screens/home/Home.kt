package com.aidev.kotlinexpert.ui.screens.home

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
@Preview
fun Home(): Unit = with(HomeState) {
    //Se carga 1 vez las notas
    val states by states.collectAsState()

    LaunchedEffect(true) {
        loadNotes(this)
    }

    MaterialTheme {
        Scaffold(
            topBar = { MyCustomTopBar(::onFilterClick ) }
        ) { padding ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize().padding(padding)
            ) {
                if(states.loading) {
                    CircularProgressIndicator()
                }
                states.filteredNotes?.let { MyNotesList(it) }
            }
        }
    }
}

