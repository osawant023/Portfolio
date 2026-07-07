package com.app.util

import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

actual fun sendDirectEmail(
    name: String,
    email: String,
    intent: String,
    message: String,
    accessKey: String,
    onSuccess: () -> Unit,
    onError: () -> Unit
) {
    thread {
        try {
            val url = URL("https://api.web3forms.com/submit")
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.setRequestProperty("Content-Type", "application/json")
            conn.doOutput = true

            val json = """
                {
                    "access_key": "$accessKey",
                    "name": "$name",
                    "email": "$email",
                    "subject": "[$intent] Message from $name",
                    "message": "$message"
                }
            """.trimIndent()

            conn.outputStream.use { os ->
                val input = json.toByteArray(Charsets.UTF_8)
                os.write(input, 0, input.size)
            }

            val code = conn.responseCode
            if (code == 200) {
                onSuccess()
            } else {
                onError()
            }
        } catch (e: Exception) {
            onError()
        }
    }
}
