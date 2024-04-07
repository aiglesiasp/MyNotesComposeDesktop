package com.aidev.kotlinexpert

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.aidev.kotlinexpert.ui.App

fun main() {
    application {
        Window(onCloseRequest = ::exitApplication, title = "My Notes") {
            App()
        }
    }
}

