package com.app.portfolio

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform