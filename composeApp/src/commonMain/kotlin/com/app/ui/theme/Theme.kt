package com.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import com.app.ui.design.DarkPortfolioColors
import com.app.ui.design.PortfolioColors
import com.app.ui.design.Spacing

private val DarkColorScheme = darkColorScheme(
    primary = Accent,
    secondary = Secondary,
    tertiary = MintAccent,
    background = DarkBackground,
    surface = DarkSurface,
    onPrimary = DarkBackground,
    onSecondary = DarkBackground,
    onBackground = DarkTextPrimary,
    onSurface = DarkTextPrimary,
    outline = DarkBorder,
    surfaceVariant = DarkCard,
    onSurfaceVariant = DarkTextSecondary
)

val LocalPortfolioColors = staticCompositionLocalOf<PortfolioColors> {
    DarkPortfolioColors
}

val LocalPortfolioSpacing = staticCompositionLocalOf { Spacing }

object PortfolioTheme {
    val colors: PortfolioColors
        @Composable @ReadOnlyComposable
        get() = LocalPortfolioColors.current

    val spacing: Spacing
        @Composable @ReadOnlyComposable
        get() = LocalPortfolioSpacing.current

    val typography: androidx.compose.material3.Typography
        @Composable @ReadOnlyComposable
        get() = MaterialTheme.typography
}

@Composable
fun PortfolioAppTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalPortfolioColors provides DarkPortfolioColors,
        LocalPortfolioSpacing provides Spacing
    ) {
        MaterialTheme(
            colorScheme = DarkColorScheme,
            typography = PortfolioTypography,
            shapes = PortfolioShapes,
            content = content
        )
    }
}
