package com.app.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.ui.theme.PortfolioTheme
import com.app.ui.theme.ChipShape

// ============================================================
// TechBadge — "Built with Jetpack Compose" animated badge
// ============================================================
@Composable
fun TechBadge(
    text: String,
    modifier: Modifier = Modifier
) {
    val colors = PortfolioTheme.colors
    val infiniteTransition = rememberInfiniteTransition(label = "badge_pulse")

    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow"
    )

    val borderAlpha by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "border"
    )

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(colors.techBadgeBackground)
            .border(
                width = 1.dp,
                color = colors.techBadgeBorderColor.copy(alpha = borderAlpha),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        // Pulsing dot
        Box(
            modifier = Modifier
                .size(6.dp)
                .background(
                    color = colors.techBadgeTextColor.copy(alpha = glowAlpha),
                    shape = CircleShape
                )
        )
        Text(
            text = text,
            color = colors.techBadgeTextColor.copy(alpha = 0.9f),
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.3.sp
        )
    }
}

// ============================================================
// SectionHeader — consistent section titles
// ============================================================
@Composable
fun SectionHeader(
    title: String,
    subtitle: String? = null,
    modifier: Modifier = Modifier
) {
    val colors = PortfolioTheme.colors
    val spacing = PortfolioTheme.spacing

    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(spacing.medium)
        ) {
            // Accent line
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(28.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(colors.accentGradientStart, colors.accentGradientEnd)
                        ),
                        shape = RoundedCornerShape(2.dp)
                    )
            )
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                color = colors.textPrimary,
                fontWeight = FontWeight.Bold
            )
        }
        if (subtitle != null) {
            Spacer(Modifier.height(spacing.small))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyLarge,
                color = colors.textSecondary,
                modifier = Modifier.padding(start = 20.dp)
            )
        }
    }
}

// ============================================================
// SkillChip — colored tag for skills
// ============================================================
@Composable
fun SkillChip(
    text: String,
    modifier: Modifier = Modifier,
    variant: ChipVariant = ChipVariant.Default
) {
    val colors = PortfolioTheme.colors

    val (bg, fg, border) = when (variant) {
        ChipVariant.Default -> Triple(colors.chipBackground, colors.chipText, colors.chipBorder)
        ChipVariant.Alt -> Triple(
            colors.chipBackgroundAlt,
            colors.textSecondary,
            colors.border
        )
        ChipVariant.Accent -> Triple(
            colors.accentGradientStart.copy(alpha = 0.15f),
            colors.accentGradientStart,
            colors.accentGradientStart.copy(alpha = 0.3f)
        )
    }

    Box(
        modifier = modifier
            .clip(ChipShape)
            .background(bg)
            .border(width = 1.dp, color = border, shape = ChipShape)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            color = fg,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

enum class ChipVariant { Default, Alt, Accent }

// ============================================================
// PortfolioCard — base card with subtle shadow/border
// ============================================================
@Composable
fun PortfolioCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val colors = PortfolioTheme.colors
    val spacing = PortfolioTheme.spacing

    Card(
        modifier = modifier
            .fillMaxWidth()
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = colors.cardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        border = BorderStroke(1.dp, colors.border)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(spacing.cardPadding),
            content = content
        )
    }
}

// ============================================================
// GradientButton
// ============================================================
@Composable
fun GradientButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: @Composable (() -> Unit)? = null
) {
    val colors = PortfolioTheme.colors

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(colors.accentGradientStart, colors.accentGradientEnd)
                )
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            icon?.invoke()
            Text(
                text = text,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }
    }
}

// ============================================================
// OutlineButton
// ============================================================
@Composable
fun OutlineButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = PortfolioTheme.colors

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, colors.primary, RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = colors.primary,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        )
    }
}

// ============================================================
// StatCard — for the About section stats
// ============================================================
@Composable
fun StatCard(
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    val colors = PortfolioTheme.colors
    val spacing = PortfolioTheme.spacing

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(colors.cardBackground)
            .border(1.dp, colors.border, RoundedCornerShape(12.dp))
            .padding(spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineLarge,
            color = colors.primary,
            fontWeight = FontWeight.Black
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = colors.textSecondary,
            fontWeight = FontWeight.Medium
        )
    }
}

// ============================================================
// AnimatedVisibilitySection — fade + slide-up on appear
// ============================================================
@Composable
fun AnimatedSection(
    visible: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = tween(600)) +
                slideInVertically(
                    animationSpec = tween(600, easing = FastOutSlowInEasing),
                    initialOffsetY = { it / 4 }
                ),
        exit = fadeOut(),
        modifier = modifier,
        content = content
    )
}

// ============================================================
// DividerLine — subtle horizontal divider
// ============================================================
@Composable
fun DividerLine(modifier: Modifier = Modifier) {
    val colors = PortfolioTheme.colors
    HorizontalDivider(
        modifier = modifier,
        thickness = 1.dp,
        color = colors.border
    )
}
