package com.gkquiz.quiz

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

fun fetchChaptersFromGitHub(): String? {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("https://raw.githubusercontent.com/nimizu/FIN_GK_BY_NIHU/main/app/src/main/java/com/gkquiz/quiz/Chapters.kt")
        .build()

    return try {
        val response: Response = client.newCall(request).execute()
        if (response.isSuccessful) {
            response.body?.string() // Return the content as a string
        } else {
            null // Handle unsuccessful response
        }
    } catch (e: Exception) {
        null // Handle exceptions
    }
}
