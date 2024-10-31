package com.gkquiz.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp



@Composable
fun ChapterSelectionScreen(onChapterSelected: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background) // Adapt to dark/light theme
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Made by Nihu",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground // Adapt to theme
        )
        Spacer(modifier = Modifier.height(16.dp))

        chapters.keys.forEach { chapter ->
            Button(
                onClick = { onChapterSelected(chapter) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary, // Button color
                    contentColor = MaterialTheme.colorScheme.onPrimary // Text color inside button
                )
            ) {
                Text(text = chapter)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}


@Composable
fun QuizScreen() {
    var selectedChapter by remember { mutableStateOf<String?>(null) } // Track selected chapter
    var questions by remember { mutableStateOf(emptyList<Question>()) } // Track questions for selected chapter
    var questionIndex by remember { mutableIntStateOf(0) }
    var score by remember { mutableIntStateOf(0) }
    var selectedOptionIndex by remember { mutableIntStateOf(-1) }
    var isQuizCompleted by remember { mutableStateOf(false) }

    if (selectedChapter == null) {
        // Show chapter selection screen if no chapter selected
        ChapterSelectionScreen { chapter ->
            selectedChapter = chapter
            questions = chapters[chapter]?.shuffled() ?: emptyList() // Load and shuffle questions for the chapter
        }
    } else if (isQuizCompleted) {
        // Show score dialog when quiz is completed
        showScore(score, questions.size) {
            // Reset to allow chapter re-selection
            selectedChapter = null
            questionIndex = 0
            score = 0
            selectedOptionIndex = -1
            isQuizCompleted = false
        }
    } else {
        // Quiz question display and logic for the selected chapter
        val currentQuestion = questions[questionIndex]
        val shuffledOptions = remember(questionIndex) { currentQuestion.options.shuffled() }

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background), // Adapt to dark/light theme
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Question ${questionIndex + 1}/${questions.size}",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary // Adapt to theme
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = currentQuestion.questionText,
                        color = MaterialTheme.colorScheme.onBackground // Adapt to theme
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // Display options with highlighting for selected
                    shuffledOptions.forEachIndexed { index, option ->
                        val backgroundColor = if (index == selectedOptionIndex) Color(0xFFD6EAF8) else MaterialTheme.colorScheme.surface
                        Text(
                            text = option,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(backgroundColor)
                                .clickable { selectedOptionIndex = index }
                                .padding(8.dp),
                            color = MaterialTheme.colorScheme.onSurface // Adapt to theme
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (selectedOptionIndex == shuffledOptions.indexOf(currentQuestion.options[currentQuestion.correctAnswer])) {
                                score++
                            }
                            selectedOptionIndex = -1 // Reset selection
                            if (questionIndex < questions.size - 1) {
                                questionIndex++
                            } else {
                                isQuizCompleted = true // Mark the quiz as complete
                            }
                        },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Save and Next")
                    }
                }
            }
        )
    }
}
