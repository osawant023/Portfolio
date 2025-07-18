package com.app.portfolio

import PortfolioApp
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.app.ui.theme.LightColorScheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme(LightColorScheme) {
        PortfolioApp()
    }
}