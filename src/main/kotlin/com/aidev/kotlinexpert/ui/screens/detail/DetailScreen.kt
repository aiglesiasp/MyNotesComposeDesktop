package com.aidev.kotlinexpert.ui.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aidev.kotlinexpert.data.Note
import kotlin.math.exp

@Composable
fun DetailScreen(vm: DetailViewModel, onClose: () -> Unit) {

    val note = vm.state.note

    Scaffold(
        topBar = {
            MyDetailTopBar(
                note = note,
                onClose = onClose,
                onSave = { vm.save() },
                onDelete = { vm.delete() }
                ) }
    ) {
        //Si la nota ha sido eliminado llamo directamente al onClose
        if(vm.state.saved) {
            onClose()
        }

        if(vm.state.loading) {
            CircularProgressIndicator()
        } else {
            Column(
                modifier = Modifier.padding(32.dp)
            ) {
                OutlinedTextField(
                    value = note.title,
                    onValueChange = { vm.update(note.copy(title = it)) },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Title") },
                    maxLines = 1
                )
                TypeDropdown(
                    value = note.type,
                    onValueChange = { vm.update(note.copy(type = it)) },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = note.description,
                    onValueChange = { vm.update(note.copy(description = it)) },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth().weight(1f)
                )
            }
        }
    }
}

@Composable
private fun TypeDropdown(value: Note.NoteType, onValueChange: (Note.NoteType) -> Unit, modifier: Modifier = Modifier) {

    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier, propagateMinConstraints = true) {
        OutlinedTextField(
            value = value.toString(),
            onValueChange = {},
            readOnly = true,
            label = { Text("NoteType") },
            trailingIcon = {
                IconButton(onClick = {expanded = true}) {
                    Icon(imageVector = Icons.Default.ArrowDropDown, "Show Note Types")
                }
            }
        )
        DropdownMenu(expanded = expanded, onDismissRequest = {expanded = false }) {
            Note.NoteType.values().forEach {
                DropdownMenuItem(onClick = {onValueChange(it); expanded = false}) {
                    Text(it.name)
                }
            }
        }
    }
}

@Composable
private fun MyDetailTopBar(note: Note, onClose: () -> Unit, onSave: () -> Unit, onDelete: () -> Unit) {
    TopAppBar(
        title = { Text(note.title) },
        navigationIcon = {
            IconButton(onClick = onClose) {
                Icon(imageVector = Icons.Default.Close, "Close Detail")
            }
        },
        actions = {
            IconButton(onClick = onSave) {
                Icon(imageVector = Icons.Default.Save, "Save Note")
            }
            if (note.id != Note.NEW_NOTE) {
                IconButton(onClick = onDelete) {
                    Icon(imageVector = Icons.Default.Delete, "Delete Note")
                }
            }
        }
    )
}