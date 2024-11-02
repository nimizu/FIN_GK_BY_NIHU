package com.gkquiz.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable


/*@Composable
fun MainScreen() {
    var isModeSelected by remember { mutableStateOf(false) }
    var isOnlineMode by remember { mutableStateOf(false) }

    if (!isModeSelected) {
        ModeSelectionScreen { isOnline ->
            isOnlineMode = isOnline
            isModeSelected = true
        }
    } else {
        QuizScreen(isOnlineMode) // Pass the mode to QuizScreen
    }
}

*/

@Composable
fun MainScreen() {
    var isOnlineMode by remember { mutableStateOf(false) } // Default to offline

    // User selects mode
    Row {
        Button(onClick = { isOnlineMode = false }) {
            Text("Offline Mode")
        }
        Button(onClick = { isOnlineMode = true }) {
            Text("Online Mode")
        }
    }

    // Display the quiz screen based on the selected mode
    QuizScreen(isOnlineMode = isOnlineMode)
}
