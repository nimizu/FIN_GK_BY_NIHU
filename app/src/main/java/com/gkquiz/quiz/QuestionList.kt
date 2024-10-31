package com.gkquiz.quiz

// Define your questions
data class Question(val questionText: String, val options: List<String>, val correctAnswer: Int)

//val questionList = listOf(
//    Question("What is the capital of France?", listOf("Berlin", "London", "Paris", "Rome"), 2),
//    Question("What is the largest planet?", listOf("Earth", "Mars", "Jupiter", "Saturn"), 2),
//    Question("Who was the First President of India?", listOf("Dr. Rajendra Prasad", "Sardar Vallabhai Patel", "Jawaharlal Nehru", "H.J Kania"), 0),
//    Question("Who was the First Vice-President of India?", listOf("Sarvepalli Radhakrishnan", "G. V. Mavlankar", "Dr. Rajendra Prasad", "Jawaharlal Nehru"), 0),
//    Question("Who was the First President of Indian National Congress?", listOf("Womesh Chandra Bannerjee", "Jawaharlal Nehru", "G. V. Mavlankar", "Sardar Vallabhai Patel"), 0),
//    Question("Who was the First Prime Minister of India?", listOf("Jawaharlal Nehru", "Sardar Vallabhai Patel", "Dr. Rajendra Prasad", "H.J Kania"), 0),
//    Question("Who was the First Chief Election Commissioner of India?", listOf("Sukumar Sen", "H.J Kania", "G. V. Mavlankar", "Sarvepalli Radhakrishnan"), 0),
//    Question("Who was the First Viceroy of India?", listOf("Lord Canning", "Sukumar Sen", "Lord William Bentinck", "H.J Kania"), 0),
//    Question("Who was the First Governor-General of India?", listOf("Lord William Bentinck", "Lord Canning", "Jawaharlal Nehru", "Dr. Rajendra Prasad"), 0),
//    Question("Who was the First Chief Justice of India?", listOf("H.J Kania", "Sukumar Sen", "Sarvepalli Radhakrishnan", "G. V. Mavlankar"), 0),
//    Question("Who was the First Speaker of Lok Sabha?", listOf("G. V. Mavlankar", "H.J Kania", "Sardar Vallabhai Patel", "Jawaharlal Nehru"), 0),
//    Question("Who was the First Home Minister of India?", listOf("Sardar Vallabhai Patel", "Sarvepalli Radhakrishnan", "Dr. Rajendra Prasad", "Jawaharlal Nehru"), 0),
//    Question("Who was the First Chairperson of Lokpal of India?", listOf("Pinaki Chandra Ghose", "H.J Kania", "Sukumar Sen", "G. V. Mavlankar"), 0),
//    Question("Who was the First Defence Minister of India?", listOf("Baldev Singh Chokkar", "Sardar Vallabhai Patel", "Jawaharlal Nehru", "H.J Kania"), 0),
//    Question("Who was the First Commander-in-Chief of Free India?", listOf("Kodandera Madappa Cariappa", "Jawaharlal Nehru", "Baldev Singh Chokkar", "Subroto Mukherjee"), 0),
//    Question("Who was the First Commander-in-Chief of the Indian Air Force?", listOf("Subroto Mukherjee", "Kodandera Madappa Cariappa", "Sam Manekshaw", "Baldev Singh Chokkar"), 0),
//    Question("Who was the First Field Marshal of India?", listOf("Sam Manekshaw", "H.J Kania", "Kodandera Madappa Cariappa", "Jawaharlal Nehru"), 0),
//    Question("Who was the First Param Vir Chakra Winner?", listOf("Major Somnath Sharma", "Sam Manekshaw", "Kodandera Madappa Cariappa", "Baldev Singh Chokkar"), 0),
//    Question("Who was the First female Jawan in the Army?", listOf("Shanti Tigga", "Avani Chaturvedi", "Major Somnath Sharma", "Sardar Vallabhai Patel"), 0),
//    Question("Who was the First Indian woman to fly a fighter aircraft without a co-pilot?", listOf("Avani Chaturvedi", "Shanti Tigga", "Sam Manekshaw", "Subroto Mukherjee"), 0),
//
//
//    // Add more questions here
//)
