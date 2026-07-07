package com.app.ui.sections

import androidx.compose.animation.core.*
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.data.Experience
import com.app.data.PortfolioData
import com.app.ui.components.ChipVariant
import com.app.ui.components.GlassCard
import com.app.ui.components.LabelCapsText
import com.app.ui.components.LabelMonoText
import com.app.ui.components.SkillChip
import com.app.ui.components.StatValue
import com.app.ui.theme.PortfolioTheme

@Composable
fun ExperienceSection(modifier: Modifier = Modifier) {
    val colors = PortfolioTheme.colors
    val spacing = PortfolioTheme.spacing

    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        val isMobile = maxWidth < 992.dp
        val rootStartPadding = if (isMobile) 10.dp else 20.dp
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = spacing.screenHorizontal.plus(rootStartPadding), end = spacing.screenHorizontal)
        ) {
            // Rotated Title / Magazine Style Area (No clipping box)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                Text(
                    text = "TIMELINE",
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = if (isMobile) 80.sp else 120.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily.SansSerif,
                        color = colors.textSecondary.copy(alpha = 0.04f),
                        letterSpacing = 4.sp
                    ),
                    modifier = Modifier
                        .graphicsLayer(
                            rotationZ = 90f,
                            translationX = if (isMobile) 80f else 120f,
                            transformOrigin = TransformOrigin(0f, 0f)
                        )
                        .offset(x = if (isMobile) 80.dp else 120.dp, y = (-20).dp)
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(bottom = 24.dp)
                ) {
                    LabelMonoText(
                        text = "Portfolio 2026",
                        color = colors.accent,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Column(modifier = Modifier.width(IntrinsicSize.Max)) {
                        Text(
                            text = "Work History",
                            style = MaterialTheme.typography.headlineMedium,
                            color = colors.primary,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.height(4.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(3.dp)
                                .background(colors.accent, RoundedCornerShape(1.5.dp))
                        )
                    }
                }
            }

            Spacer(Modifier.height(spacing.large))

            if (isMobile) {
                // Stack vertically on mobile screen configurations
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(spacing.xlarge)
                ) {
                    // Timeline Items (pushed 32.dp to avoid left clipping)
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 32.dp)
                    ) {
                        PortfolioData.experiences.forEachIndexed { index, experience ->
                            TimelineItem(
                                experience = experience,
                                index = index,
                                isLast = index == PortfolioData.experiences.lastIndex
                            )
                        }
                    }

                    // Stats Column
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(spacing.large)
                    ) {
                        ExperienceLevelCard()
                        CoreStackCard()
                    }
                }
            } else {
                // Side-by-side Row layout on larger screens
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(spacing.large)
                ) {
                    // Timeline Column (pushed 32.dp for clean alignment)
                    Column(
                        modifier = Modifier
                            .weight(2f)
                            .padding(start = 32.dp)
                    ) {
                        PortfolioData.experiences.forEachIndexed { index, experience ->
                            TimelineItem(
                                experience = experience,
                                index = index,
                                isLast = index == PortfolioData.experiences.lastIndex
                            )
                        }
                    }

                    // Right Stats Column
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(spacing.large)
                    ) {
                        ExperienceLevelCard()
                        CoreStackCard()
                        DecorativeCard()
                    }
                }
            }

            Spacer(Modifier.height(spacing.xxlarge))

            // Moved EducationSection to Timeline Tab at bottom
            EducationSection()
        }
    }
}

@Composable
private fun ExperienceLevelCard() {
    val colors = PortfolioTheme.colors
    val spacing = PortfolioTheme.spacing
    GlassCard {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.cardPadding)
        ) {
            LabelMonoText(
                text = "EXPERIENCE_LEVEL",
                color = colors.accent,
                modifier = Modifier.padding(bottom = spacing.medium)
            )
            Text(
                text = "7+",
                style = MaterialTheme.typography.displayMedium,
                color = colors.primary,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = (-2).sp
            )
            LabelCapsText(
                text = "Years Tenure",
                modifier = Modifier.padding(bottom = spacing.medium)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(colors.border.copy(alpha = 0.15f))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(colors.accent)
                )
            }
        }
    }
}

@Composable
private fun CoreStackCard() {
    val colors = PortfolioTheme.colors
    val spacing = PortfolioTheme.spacing
    GlassCard {
        Column(modifier = Modifier.padding(spacing.cardPadding)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(bottom = spacing.medium)
            ) {
                Text(
                    text = "~",
                    color = colors.accent,
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Monospace
                )
                LabelMonoText(text = "Core Stack", color = colors.primary)
            }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf(
                    "Architecture" to "SOLID",
                    "Language" to "Kotlin Expert",
                    "CI/CD" to "GitHub Actions",
                    "Performance" to "Memory Mgmt"
                ).forEach { (label, value) ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = label,
                            style = MaterialTheme.typography.bodySmall,
                            color = colors.textSecondary
                        )
                        Text(
                            text = value,
                            style = MaterialTheme.typography.labelLarge,
                            color = colors.accent,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DecorativeCard() {
    val colors = PortfolioTheme.colors
    val spacing = PortfolioTheme.spacing
    GlassCard {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .padding(spacing.cardPadding),
            contentAlignment = Alignment.BottomStart
        ) {
            Column {
                LabelCapsText(
                    text = "CRAFTING",
                    color = colors.primary
                )
                Text(
                    text = "Digital\nArchitecture",
                    style = MaterialTheme.typography.headlineSmall,
                    color = colors.primary,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun TimelineItem(
    experience: Experience,
    index: Int,
    isLast: Boolean,
    modifier: Modifier = Modifier
) {
    val colors = PortfolioTheme.colors
    val spacing = PortfolioTheme.spacing

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Left timeline column (width adjusted to 40.dp, with safety start padding)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(40.dp)
                .fillMaxHeight()
        ) {
            // Number indicator
            Text(
                text = (index + 1).toString().padStart(2, '0'),
                style = MaterialTheme.typography.titleMedium,
                color = colors.accent.copy(alpha = 0.2f),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
            Spacer(Modifier.height(16.dp))

            // Pulse node and vertical lines
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.TopCenter
            ) {
                // Vertical connector line
                if (!isLast) {
                    Box(
                        modifier = Modifier
                            .width(2.dp)
                            .fillMaxHeight()
                            .background(colors.timelineLine.copy(alpha = 0.25f))
                    )
                }

                val infiniteTransition = rememberInfiniteTransition(label = "pulse")
                val pulseAlpha by infiniteTransition.animateFloat(
                    initialValue = 0.2f,
                    targetValue = 0.8f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(1200, easing = FastOutSlowInEasing),
                        repeatMode = RepeatMode.Reverse
                    ),
                    label = "pulseAlpha"
                )

                // Dot container aligned to top of row content
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(32.dp)
                        .offset(y = (-8).dp)
                ) {
                    if (experience.isCurrent) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .background(
                                    color = colors.accent.copy(alpha = pulseAlpha),
                                    shape = CircleShape
                                )
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(14.dp)
                            .background(colors.background, CircleShape)
                            .border(
                                width = 2.dp,
                                color = if (experience.isCurrent) colors.timelineDot else colors.border,
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .background(
                                    color = if (experience.isCurrent) colors.timelineDot else colors.border,
                                    shape = CircleShape
                                )
                        )
                    }
                }
            }
        }

        // Right card content column
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 32.dp)
        ) {
            // Period label
            LabelMonoText(
                text = experience.period,
                color = colors.textSecondary.copy(alpha = 0.6f),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Glass card containing role and highlights
            GlassCard {
                Column(modifier = Modifier.padding(spacing.cardPadding)) {
                    Text(
                        text = experience.role,
                        style = MaterialTheme.typography.headlineSmall,
                        color = colors.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "@ ${experience.company}",
                        style = MaterialTheme.typography.titleSmall,
                        color = colors.accent,
                        fontFamily = FontFamily.Monospace,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Highlights rendered dynamically
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        experience.highlights.forEach { bullet ->
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Text(
                                    text = "•",
                                    color = colors.accent,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = bullet,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = colors.textSecondary,
                                    lineHeight = 22.sp
                                )
                            }
                        }
                    }

                    // Tech Stack tag chips dynamically generated in a FlowRow to wrap on mobile
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        val tags = when (experience.company.lowercase()) {
                            "neosoft" -> listOf("KMP/CMP", "ARCore", "UPI SDK", "Compose")
                            "inovant solutions" -> listOf("MVVM", "Room", "Animations", "Play Store")
                            else -> listOf("ML Kit", "SQLite", "REST APIs", "Java")
                        }
                        tags.forEach { tag ->
                            SkillChip(text = tag, variant = ChipVariant.Default)
                        }
                    }
                }
            }
        }
    }
}
