package com.app.util

import kotlinx.browser.window
import org.w3c.fetch.RequestInit
import org.w3c.fetch.Headers

// Top-level single-expression JS helper to construct RequestInit safely without compilation errors
private fun createRequestInit(headers: Headers, bodyJson: String): RequestInit = js("""
    ({
        method: "POST",
        headers: headers,
        body: bodyJson
    })
""")

actual fun sendDirectEmail(
    name: String,
    email: String,
    intent: String,
    message: String,
    accessKey: String,
    onSuccess: () -> Unit,
    onError: () -> Unit
) {
    val subject = "[$intent] Message from $name"
    val payloadJson = """
        {
            "access_key": "$accessKey",
            "name": "$name",
            "email": "$email",
            "subject": "$subject",
            "message": "$message"
        }
    """.trimIndent()

    val headers = Headers()
    headers.append("Content-Type", "application/json")
    headers.append("Accept", "application/json")

    val requestInit = createRequestInit(headers, payloadJson)

    window.fetch("https://api.web3forms.com/submit", requestInit)
        .then { response ->
            if (response.ok) {
                onSuccess()
            } else {
                onError()
            }
            null
        }
        .catch {
            onError()
            null
        }
}
