package com.app.portfolio

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalUriHandler
import com.app.ui.PortfolioScreen
import com.app.ui.theme.PortfolioAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    PortfolioAppTheme {
        val uriHandler = LocalUriHandler.current
        PortfolioScreen(
            onEmailClick = {

            },
            onOpenUrl = {
                uriHandler.openUri(it)
            },
            onPhoneClick = {

            }
        )
    }
}