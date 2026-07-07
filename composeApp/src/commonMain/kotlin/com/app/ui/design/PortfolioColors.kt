package com.app.ui.design

import androidx.compose.ui.graphics.Color
import com.app.ui.theme.Accent
import com.app.ui.theme.DarkBackground
import com.app.ui.theme.DarkBorder
import com.app.ui.theme.DarkCard
import com.app.ui.theme.DarkCardElevated
import com.app.ui.theme.DarkSurface
import com.app.ui.theme.DarkSurfaceContainerHighest
import com.app.ui.theme.DarkTextPrimary
import com.app.ui.theme.DarkTextSecondary
import com.app.ui.theme.GlassCardBg
import com.app.ui.theme.GlassCardBorder
import com.app.ui.theme.GradientEnd
import com.app.ui.theme.GradientStart
import com.app.ui.theme.MintAccent
import com.app.ui.theme.Primary
import com.app.ui.theme.Secondary
import com.app.ui.theme.TechBadgeBg
import com.app.ui.theme.TechBadgeBorder
import com.app.ui.theme.TechBadgeText
import com.app.ui.theme.TimelineDot
import com.app.ui.theme.TimelineLine

data class PortfolioColors(
    val primary: Color,
    val secondary: Color,
    val accent: Color,
    val mintAccent: Color,
    val background: Color,
    val surface: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val cardBackground: Color,
    val cardBackgroundElevated: Color,
    val surfaceContainerHighest: Color,
    val border: Color,
    val glassCardBg: Color,
    val glassCardBorder: Color,

    val heroBackground: Color,
    val sectionBackground: Color,
    val alternateBackground: Color,
    val accentGradientStart: Color,
    val accentGradientEnd: Color,

    val techBadgeBackground: Color,
    val techBadgeBorderColor: Color,
    val techBadgeTextColor: Color,

    val timelineLine: Color,
    val timelineDot: Color,

    val chipBackground: Color,
    val chipText: Color,
    val chipBorder: Color,
    val chipBackgroundAlt: Color,

    val isDark: Boolean
)

val DarkPortfolioColors = PortfolioColors(
    primary = Primary,
    secondary = Secondary,
    accent = Accent,
    mintAccent = MintAccent,
    background = DarkBackground,
    surface = DarkSurface,
    textPrimary = DarkTextPrimary,
    textSecondary = DarkTextSecondary,
    cardBackground = DarkCard,
    cardBackgroundElevated = DarkCardElevated,
    surfaceContainerHighest = DarkSurfaceContainerHighest,
    border = DarkBorder,
    glassCardBg = GlassCardBg,
    glassCardBorder = GlassCardBorder,
    heroBackground = DarkBackground,
    sectionBackground = DarkSurface,
    alternateBackground = DarkCard,
    accentGradientStart = GradientStart,
    accentGradientEnd = GradientEnd,
    techBadgeBackground = TechBadgeBg,
    techBadgeBorderColor = TechBadgeBorder,
    techBadgeTextColor = TechBadgeText,
    timelineLine = TimelineLine,
    timelineDot = TimelineDot,
    chipBackground = DarkSurfaceContainerHighest,
    chipText = Primary,
    chipBorder = DarkBorder,
    chipBackgroundAlt = DarkCard,
    isDark = true
)
