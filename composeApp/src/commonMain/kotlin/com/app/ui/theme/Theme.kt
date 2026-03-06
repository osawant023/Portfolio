package com.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import com.app.ui.design.DarkPortfolioColors
import com.app.ui.design.LightPortfolioColors
import com.app.ui.design.PortfolioColors
import com.app.ui.design.Spacing

// ============================================================
// Material 3 Color Schemes
// ============================================================
private val DarkColorScheme = darkColorScheme(
    primary = Primary,
    secondary = Secondary,
    tertiary = Accent,
    background = DarkBackground,
    surface = DarkSurface,
    onPrimary = DarkBackground,
    onSecondary = DarkBackground,
    onBackground = DarkTextPrimary,
    onSurface = DarkTextPrimary,
    outline = DarkBorder
)

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    secondary = Secondary,
    tertiary = Accent,
    background = LightBackground,
    surface = LightSurface,
    onPrimary = LightBackground,
    onSecondary = LightBackground,
    onBackground = LightTextPrimary,
    onSurface = LightTextPrimary,
    outline = LightBorder
)

// ============================================================
// Composition Locals
// ============================================================
val LocalPortfolioColors = staticCompositionLocalOf<PortfolioColors> {
    DarkPortfolioColors
}

val LocalPortfolioSpacing = staticCompositionLocalOf { Spacing }

// ============================================================
// PortfolioTheme — central access point
// Usage: PortfolioTheme.colors.primary
//        PortfolioTheme.spacing.medium
//        PortfolioTheme.typography.bodyLarge
// ============================================================
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

// ============================================================
// Theme Composable
// ============================================================
@Composable
fun PortfolioAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val portfolioColors = if (darkTheme) DarkPortfolioColors else LightPortfolioColors
    val materialColorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    CompositionLocalProvider(
        LocalPortfolioColors provides portfolioColors,
        LocalPortfolioSpacing provides Spacing
    ) {
        MaterialTheme(
            colorScheme = materialColorScheme,
            typography = PortfolioTypography,
            shapes = PortfolioShapes,
            content = content
        )
    }
}
