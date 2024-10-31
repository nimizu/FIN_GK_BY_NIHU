package com.gkquiz.quiz

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import kotlin.system.exitProcess

@Composable
fun showScore(score: Int, totalQuestions: Int, onRestart: () -> Unit) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text("Quiz Complete!") },
        text = { Text("Your score: $score/$totalQuestions") },
        confirmButton = {
            Button(onClick = onRestart) {
                Text("Restart")
            }
        },
        dismissButton = {
            Button(onClick = { exitProcess(0) }) {
                Text("Exit")
            }
        }
    )
}
