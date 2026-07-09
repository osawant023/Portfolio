package com.app.util

expect fun sendDirectEmail(
    name: String,
    email: String,
    intent: String,
    message: String,
    accessKey: String,
    onSuccess: () -> Unit,
    onError: () -> Unit
)
