package com.app.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.ui.theme.PortfolioTheme
import com.app.ui.theme.ChipShape

// ============================================================
// GlassCard — frosted glass card matching Stitch design
// ============================================================
@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val colors = PortfolioTheme.colors

    val mod = modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(8.dp))
        .background(colors.glassCardBg)
        .border(1.dp, colors.glassCardBorder, RoundedCornerShape(8.dp))
        .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)

    Card(
        modifier = mod,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth(), content = content)
    }
}

// ============================================================
// SectionHeader — magazine-style title with accent underline
// ============================================================
@Composable
fun SectionHeader(
    title: String,
    subtitle: String? = null,
    modifier: Modifier = Modifier,
    labelPrefix: String? = null
) {
    val colors = PortfolioTheme.colors
    val spacing = PortfolioTheme.spacing

    Column(modifier = modifier) {
        if (labelPrefix != null) {
            Text(
                text = labelPrefix,
                style = MaterialTheme.typography.labelLarge,
                color = colors.accent.copy(alpha = 0.7f),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        Column(modifier = Modifier.width(IntrinsicSize.Min)) {
            Text(
                text = title,
                style = MaterialTheme.typography.displaySmall,
                color = colors.primary,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-1).sp
            )
            Spacer(Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(3.dp)
                    .background(
                        color = colors.accent,
                        shape = RoundedCornerShape(1.5.dp)
                    )
            )
        }
        Spacer(Modifier.height(spacing.small))
        if (subtitle != null) {
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = colors.textSecondary,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

// ============================================================
// TechBadge — "label-caps" style chip (uppercase, small)
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
        initialValue = 0.3f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "border"
    )

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(colors.techBadgeBackground)
            .border(
                width = 1.dp,
                color = colors.techBadgeBorderColor.copy(alpha = borderAlpha),
                shape = RoundedCornerShape(4.dp)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = ">",
            color = colors.accent.copy(alpha = glowAlpha),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
        Text(
            text = text,
            color = colors.techBadgeTextColor.copy(alpha = 0.85f),
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.Monospace,
            letterSpacing = 0.5.sp
        )
    }
}

// ============================================================
// SkillChip — label-caps style chip (uppercase, sans-serif)
// ============================================================
@Composable
fun SkillChip(
    text: String,
    modifier: Modifier = Modifier,
    variant: ChipVariant = ChipVariant.Default
) {
    val colors = PortfolioTheme.colors

    val (bg, fg, border) = when (variant) {
        ChipVariant.Default -> Triple(colors.surfaceContainerHighest, colors.primary, colors.border)
        ChipVariant.Alt -> Triple(colors.cardBackground, colors.textSecondary, colors.border)
        ChipVariant.Accent -> Triple(
            colors.accent.copy(alpha = 0.12f),
            colors.accent,
            colors.accent.copy(alpha = 0.3f)
        )
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(2.dp))
            .background(bg)
            .border(width = 1.dp, color = border, shape = RoundedCornerShape(2.dp))
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            color = fg,
            fontSize = 12.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.SansSerif,
            letterSpacing = 0.1.sp
        )
    }
}

enum class ChipVariant { Default, Alt, Accent }

// ============================================================
// PortfolioCard — glass-style card (matching Stitch glass-card)
// ============================================================
@Composable
fun PortfolioCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    GlassCard(modifier = modifier, onClick = onClick, content = content)
}

// ============================================================
// GradientButton — mint-green gradient button
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
            .clip(RoundedCornerShape(8.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(colors.accent, colors.mintAccent)
                )
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            icon?.invoke()
            Text(
                text = text,
                color = Color(0xFF0D0D0D),
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}

// ============================================================
// OutlineButton — accent outline
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
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, colors.accent, RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = colors.accent,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            fontFamily = FontFamily.Monospace
        )
    }
}

// ============================================================
// StatCard — glass-style stat readout
// ============================================================
@Composable
fun StatCard(
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    val colors = PortfolioTheme.colors
    val spacing = PortfolioTheme.spacing

    GlassCard(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.medium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.headlineLarge,
                color = colors.accent,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = colors.textSecondary,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily.Monospace
            )
        }
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
// DividerLine — subtle divider
// ============================================================
@Composable
fun DividerLine(modifier: Modifier = Modifier) {
    val colors = PortfolioTheme.colors
    HorizontalDivider(
        modifier = modifier,
        thickness = 1.dp,
        color = colors.border.copy(alpha = 0.3f)
    )
}

// ============================================================
// LabelCapsText — uppercase, small, letter-spaced (label-caps)
// ============================================================
@Composable
fun LabelCapsText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color? = null
) {
    val colors = PortfolioTheme.colors
    Text(
        text = text.uppercase(),
        modifier = modifier,
        style = MaterialTheme.typography.labelMedium,
        color = color ?: colors.textSecondary,
        letterSpacing = 1.2.sp,
        fontWeight = FontWeight.ExtraBold
    )
}

// ============================================================
// LabelMonoText — JetBrains Mono style label
// ============================================================
@Composable
fun LabelMonoText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color? = null
) {
    val colors = PortfolioTheme.colors
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.labelLarge,
        color = color ?: colors.textSecondary,
        fontFamily = FontFamily.Monospace
    )
}

// ============================================================
// DotBullet — mint-green dot indicator
// ============================================================
@Composable
fun DotBullet(
    modifier: Modifier = Modifier
) {
    val colors = PortfolioTheme.colors
    Box(
        modifier = modifier
            .size(6.dp)
            .clip(RoundedCornerShape(50))
            .background(colors.accent)
    )
}

// ============================================================
// StatValue — large stat display like "12+"
// ============================================================
@Composable
fun StatValue(
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    val colors = PortfolioTheme.colors
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.displayMedium,
            color = colors.primary,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = (-2).sp
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = colors.textSecondary,
            fontFamily = FontFamily.Monospace,
            letterSpacing = 0.5.sp
        )
    }
}
