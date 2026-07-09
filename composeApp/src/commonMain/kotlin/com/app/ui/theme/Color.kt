package com.app.ui.theme

import androidx.compose.ui.graphics.Color

// === PRIMARY PALETTE — Mint-green accent (Stitch Design) ===
val Primary = Color(0xFFFFFFFF)           // White text
val PrimaryVariant = Color(0xFF7CFBA8)
val Secondary = Color(0xFFC0C7D1)          // Light gray
val Accent = Color(0xFF5FDE8E)             // Mint accent (primary-fixed-dim)
val MintAccent = Color(0xFF80FFAB)         // Mint glow

// === DARK THEME ===
val DarkBackground = Color(0xFF0E1510)     // Dark green-black
val DarkSurface = Color(0xFF0E1510)
val DarkCard = Color(0xFF1A211B)           // Surface container
val DarkCardElevated = Color(0xFF252C26)   // Surface container high
val DarkBorder = Color(0xFF3D4A3F)         // Outline variant
val DarkTextPrimary = Color(0xFFDDE4DB)    // On surface
val DarkTextSecondary = Color(0xFFBCCABC)  // On surface variant
val DarkSurfaceContainerHighest = Color(0xFF303630)

// === GRADIENTS ===
val GradientStart = Color(0xFF5FDE8E)      // Mint accent
val GradientEnd = Color(0xFF7CFBA8)        // Primary fixed
val HeroGradientStart = Color(0xFF0E1510)
val HeroGradientEnd = Color(0xFF1A211B)

// === SEMANTIC ===
val Success = Color(0xFF5FDE8E)
val Warning = Color(0xFFFCE09F)            // Tertiary fixed
val Error = Color(0xFFFFB4AB)              // Error
val Info = Color(0xFF7CFBA8)

// === TECH BADGE ===
val TechBadgeBg = DarkSurfaceContainerHighest
val TechBadgeBorder = Color(0xFF3D4A3F)
val TechBadgeText = Primary

// === TIMELINE ===
val TimelineLine = Color(0xFF3D4A3F)
val TimelineDot = Accent
val GlassCardBg = Color(0x99303630)        // rgba(48,54,48,0.6)
val GlassCardBorder = Color(0x1ABCCABC)    // rgba(188,202,188,0.1)
