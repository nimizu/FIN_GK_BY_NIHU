package com.gkquiz.quiz

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

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

@Composable
fun QuizScreen(isOnlineMode: Boolean) {
    var selectedChapter by remember { mutableStateOf<String?>(null) }
    var questions by remember { mutableStateOf(emptyList<Question>()) }
    var questionIndex by remember { mutableIntStateOf(0) }
    var score by remember { mutableIntStateOf(0) }
    var isQuizCompleted by remember { mutableStateOf(false) }

    // Track selected answers and feedback state for each question
    var selectedOptionIndex by remember { mutableIntStateOf(-1) }
    var showFeedback by remember { mutableStateOf(false) }
    var allowNext by remember { mutableStateOf(false) }

    // Track answer history
    val answerHistory = remember { mutableStateListOf<Int?>() }

    if (selectedChapter == null) {
        ChapterSelectionScreen { chapter ->
            selectedChapter = chapter
            // Load questions based on the mode
            questions = if (isOnlineMode) {
                // Fetch chapters from GitHub or wherever you have the online data
                val chaptersString = fetchChaptersFromGitHub() // You need to implement this
                // Parse and shuffle questions based on the selected chapter
                chapters[chapter]?.shuffled() ?: emptyList()
            } else {
                // Load local chapters here
                chapters[chapter]?.shuffled() ?: emptyList()
            }
            answerHistory.clear()
            repeat(questions.size) { answerHistory.add(null) }
        }
    } else if (isQuizCompleted) {
        showScore(score, questions.size) {
            selectedChapter = null
            questionIndex = 0
            score = 0
            selectedOptionIndex = -1
            isQuizCompleted = false
            allowNext = false
            showFeedback = false
            answerHistory.clear()
            repeat(questions.size) { answerHistory.add(null) }
        }
    } else {
        val currentQuestion = questions[questionIndex]
        val shuffledOptions = remember(questionIndex) { currentQuestion.options.shuffled() }

        // Restore previous answer if available
        selectedOptionIndex = answerHistory[questionIndex] ?: -1

        Scaffold(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
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
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = currentQuestion.questionText,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // Highlight correct and incorrect answers
                    shuffledOptions.forEachIndexed { index, option ->
                        val backgroundColor = when {
                            showFeedback && option == currentQuestion.options[currentQuestion.correctAnswer] -> Color.Green
                            showFeedback && index == selectedOptionIndex -> Color.Red
                            index == selectedOptionIndex -> Color(0xFFD6EAF8) // Light blue for selected option
                            else -> MaterialTheme.colorScheme.surface
                        }
                        Text(
                            text = option,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(backgroundColor)
                                .clickable(enabled = !showFeedback) {
                                    selectedOptionIndex = index
                                    answerHistory[questionIndex] = selectedOptionIndex
                                }
                                .padding(8.dp),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Save and Next Button functionality
                    Row {
                        if (!showFeedback) {
                            Button(
                                onClick = {
                                    showFeedback = true
                                    allowNext = true

                                    // Check answer correctness
                                    if (selectedOptionIndex == shuffledOptions.indexOf(currentQuestion.options[currentQuestion.correctAnswer])) {
                                        score++
                                    }
                                },
                                modifier = Modifier.weight(1f),
                                enabled = selectedOptionIndex != -1 // Enable only if an option is selected
                            ) {
                                Text("Save")
                            }
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        if (allowNext) {
                            Button(
                                onClick = {
                                    showFeedback = false
                                    allowNext = false
                                    selectedOptionIndex = -1
                                    if (questionIndex < questions.size - 1) {
                                        questionIndex++
                                    } else {
                                        isQuizCompleted = true
                                    }
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Next")
                            }
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Button(
                            onClick = {
                                if (questionIndex > 0) {
                                    questionIndex--
                                    showFeedback = true
                                    selectedOptionIndex = answerHistory[questionIndex] ?: -1
                                    allowNext = selectedOptionIndex != -1
                                }
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Back")
                        }
                    }
                }
            }
        )
    }
}
