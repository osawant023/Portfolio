package com.app.util

import platform.Foundation.*
import platform.darwin.*

actual fun sendDirectEmail(
    name: String,
    email: String,
    intent: String,
    message: String,
    accessKey: String,
    onSuccess: () -> Unit,
    onError: () -> Unit
) {
    val url = NSURL(string = "https://api.web3forms.com/submit") ?: return
    val request = NSMutableURLRequest(uRL = url)
    request.setHTTPMethod("POST")
    request.setValue("application/json", forHTTPHeaderField = "Content-Type")

    val jsonString = """
        {
            "access_key": "$accessKey",
            "name": "$name",
            "email": "$email",
            "subject": "[$intent] Message from $name",
            "message": "$message"
        }
    """.trimIndent()

    val data = (jsonString as NSString).dataUsingEncoding(NSUTF8StringEncoding)
    request.setHTTPBody(data)

    val session = NSURLSession.sharedSession()
    val task = session.dataTaskWithRequest(request) { _, response, error ->
        dispatch_async(dispatch_get_main_queue()) {
            val httpResponse = response as? NSHTTPURLResponse
            if (error == null && httpResponse?.statusCode == 200L) {
                onSuccess()
            } else {
                onError()
            }
        }
    }
    task.resume()
}
