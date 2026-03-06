package com.app.ui.design

import androidx.compose.ui.unit.dp

/**
 * Centralized spacing system for consistent layout throughout the app.
 * Usage: PortfolioTheme.spacing.medium
 */
object Spacing {
    /** 4.dp - micro spacing */
    val micro = 4.dp

    /** 8.dp - small spacing (between related elements) */
    val small = 8.dp

    /** 12.dp - compact spacing */
    val compact = 12.dp

    /** 16.dp - medium spacing (standard padding) */
    val medium = 16.dp

    /** 24.dp - large spacing (card padding, section gaps) */
    val large = 24.dp

    /** 32.dp - xlarge spacing */
    val xlarge = 32.dp

    /** 48.dp - xxlarge spacing */
    val xxlarge = 48.dp

    /** 64.dp - section spacing (between major sections) */
    val section = 64.dp

    /** 80.dp - hero spacing */
    val hero = 80.dp

    // Common content padding
    val screenHorizontal = 20.dp
    val cardPadding = 20.dp
    val chipPadding = 10.dp
}
