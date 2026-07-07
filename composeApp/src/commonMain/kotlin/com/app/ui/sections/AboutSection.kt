package com.app.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.ui.components.GlassCard
import com.app.ui.components.LabelMonoText
import com.app.ui.components.SectionHeader
import com.app.ui.components.StatCard
import com.app.ui.theme.PortfolioTheme
import com.app.data.PortfolioData
import com.app.ui.components.LabelCapsText

import androidx.compose.animation.core.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign

@Composable
fun AboutSection(modifier: Modifier = Modifier) {
    val colors = PortfolioTheme.colors
    val spacing = PortfolioTheme.spacing

    val infiniteTransition = rememberInfiniteTransition(label = "location_pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_scale"
    )
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_alpha"
    )

    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        val isMobile = maxWidth < 600.dp

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.screenHorizontal)
                .padding(vertical = spacing.section)
        ) {
            LabelMonoText(
                text = "BIO",
                color = colors.accent,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "About Me",
                style = MaterialTheme.typography.headlineMedium,
                color = colors.primary,
                fontWeight = FontWeight.Bold
            )
            Box(
                modifier = Modifier
                    .width(96.dp)
                    .height(4.dp)
                    .padding(top = 12.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(colors.accent)
            )

            Spacer(Modifier.height(spacing.large))

            GlassCard {
                Column(modifier = Modifier.padding(spacing.cardPadding)) {
                    Text(
                        text = PortfolioData.aboutSummary,
                        style = MaterialTheme.typography.bodyLarge,
                        color = colors.textSecondary
                    )
                }
            }

            Spacer(Modifier.height(spacing.large))

            // Metric Cards (2x2 Grid on mobile, horizontal row of 4 on desktop)
            if (isMobile) {
                val chunks = PortfolioData.aboutStats.chunked(2)
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(spacing.medium)
                ) {
                    chunks.forEach { rowStats ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(spacing.medium)
                        ) {
                            rowStats.forEach { (value, label) ->
                                StatCard(
                                    value = value,
                                    label = label,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(spacing.large)
                ) {
                    PortfolioData.aboutStats.forEach { (value, label) ->
                        StatCard(
                            value = value,
                            label = label,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            Spacer(Modifier.height(spacing.large))

            // Base Location Card (Responsive layout)
            GlassCard {
                Column(modifier = Modifier.padding(spacing.cardPadding)) {
                    if (isMobile) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                // Pulse Dot Container
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(16.dp)
                                            .alpha(pulseAlpha)
                                            .background(colors.accent.copy(alpha = 0.4f), RoundedCornerShape(50.dp))
                                    )
                                    Box(
                                        modifier = Modifier
                                            .size(8.dp)
                                            .background(colors.accent, RoundedCornerShape(50.dp))
                                    )
                                }

                                Column {
                                    LabelMonoText(
                                        text = "CURRENT BASE",
                                        color = colors.textSecondary.copy(alpha = 0.5f)
                                    )
                                    Spacer(Modifier.height(4.dp))
                                    Text(
                                        text = PortfolioData.location,
                                        style = MaterialTheme.typography.headlineSmall,
                                        color = colors.primary,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "India (GMT +5:30) • ${PortfolioData.phone}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = colors.textSecondary.copy(alpha = 0.8f)
                                    )
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(colors.border.copy(alpha = 0.15f))
                            )

                            Column(horizontalAlignment = Alignment.Start) {
                                Text(
                                    text = "// available for",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = colors.textSecondary.copy(alpha = 0.6f),
                                    fontFamily = FontFamily.Monospace
                                )
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    text = "full_time | contract",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = colors.accent,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = FontFamily.Monospace
                                )
                            }
                        }
                    } else {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                // Pulse Dot Container
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(16.dp)
                                            .alpha(pulseAlpha)
                                            .background(colors.accent.copy(alpha = 0.4f), RoundedCornerShape(50.dp))
                                    )
                                    Box(
                                        modifier = Modifier
                                            .size(8.dp)
                                            .background(colors.accent, RoundedCornerShape(50.dp))
                                    )
                                }

                                Column {
                                    LabelMonoText(
                                        text = "CURRENT BASE",
                                        color = colors.textSecondary.copy(alpha = 0.5f)
                                    )
                                    Spacer(Modifier.height(4.dp))
                                    Text(
                                        text = PortfolioData.location,
                                        style = MaterialTheme.typography.headlineSmall,
                                        color = colors.primary,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "India (GMT +5:30) • ${PortfolioData.phone}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = colors.textSecondary.copy(alpha = 0.8f)
                                    )
                                }
                            }

                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    text = "// available for",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = colors.textSecondary.copy(alpha = 0.6f),
                                    fontFamily = FontFamily.Monospace
                                )
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    text = "full_time | contract",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = colors.accent,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = FontFamily.Monospace
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
