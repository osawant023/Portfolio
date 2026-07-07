package com.app.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.data.PortfolioData
import com.app.data.Project
import com.app.ui.components.ChipVariant
import com.app.ui.components.GlassCard
import com.app.ui.components.LabelMonoText
import com.app.ui.components.SkillChip
import com.app.ui.components.StatValue
import com.app.ui.theme.PortfolioTheme

@Composable
fun ProjectsSection(modifier: Modifier = Modifier, onOpenUrl: (String) -> Unit = {}) {
    val colors = PortfolioTheme.colors
    val spacing = PortfolioTheme.spacing

    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        val isMobile = maxWidth < 768.dp

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = spacing.section)
        ) {
            Column(modifier = Modifier.padding(horizontal = spacing.screenHorizontal)) {
                LabelMonoText(
                    text = "CASE_STUDIES_LOG_v2.0",
                    color = colors.textSecondary.copy(alpha = 0.6f),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Column(modifier = Modifier.width(IntrinsicSize.Min)) {
                    Text(
                        text = "SELECTED_WORKS",
                        style = MaterialTheme.typography.displaySmall,
                        color = colors.primary,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = (-2).sp
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

            Spacer(Modifier.height(spacing.xlarge))

            if (isMobile) {
                // Stack vertically on mobile screen configurations
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spacing.screenHorizontal),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    PortfolioData.projects.forEach { project ->
                        ProjectCard(
                            project = project,
                            onOpenUrl = onOpenUrl,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            } else {
                // 2-column manual staggered grid for desktop/wider screens
                val leftColumnProjects = PortfolioData.projects.filterIndexed { index, _ -> index % 2 == 0 }
                val rightColumnProjects = PortfolioData.projects.filterIndexed { index, _ -> index % 2 != 0 }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spacing.screenHorizontal),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    // Left Column (Even indices)
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        leftColumnProjects.forEach { project ->
                            ProjectCard(
                                project = project,
                                onOpenUrl = onOpenUrl,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }

                    // Right Column (Odd indices)
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        rightColumnProjects.forEach { project ->
                            ProjectCard(
                                project = project,
                                onOpenUrl = onOpenUrl,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(spacing.medium))

            // Stats Tile & Next Up section
            if (isMobile) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spacing.screenHorizontal),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Stats Card
                    GlassCard(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(spacing.cardPadding),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(spacing.large)
                        ) {
                            StatValue(value = "12+", label = "Ships_to_Production")
                            StatValue(value = "45k+", label = "Active_Installs_SDK")
                        }
                    }

                    // Next Up Card
                    GlassCard(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(spacing.cardPadding)) {
                            LabelMonoText(
                                text = "NEXT_UP: ON_DEVICE_AI",
                                color = colors.accent,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "Generative AI for Mobile",
                                style = MaterialTheme.typography.titleLarge,
                                color = colors.primary,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = "Currently exploring the intersection of Large Language Models and Android on-device processing. Building private, high-fidelity AI tools for developers.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = colors.textSecondary,
                                lineHeight = 20.sp
                            )
                        }
                    }
                }
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spacing.screenHorizontal),
                    horizontalArrangement = Arrangement.spacedBy(spacing.large)
                ) {
                    // Stats Card
                    GlassCard(modifier = Modifier.weight(1f)) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(spacing.cardPadding),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(spacing.large)
                        ) {
                            StatValue(value = "12+", label = "Ships_to_Production")
                            StatValue(value = "45k+", label = "Active_Installs_SDK")
                        }
                    }

                    // Next Up Card
                    GlassCard(modifier = Modifier.weight(1.5f)) {
                        Column(modifier = Modifier.padding(spacing.cardPadding)) {
                            LabelMonoText(
                                text = "NEXT_UP: ON_DEVICE_AI",
                                color = colors.accent,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "Generative AI for Mobile",
                                style = MaterialTheme.typography.titleLarge,
                                color = colors.primary,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = "Currently exploring the intersection of Large Language Models and Android on-device processing. Building private, high-fidelity AI tools for developers.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = colors.textSecondary,
                                lineHeight = 20.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ProjectCard(
    project: Project,
    onOpenUrl: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = PortfolioTheme.colors
    val spacing = PortfolioTheme.spacing

    GlassCard(modifier = modifier) {
        Column(modifier = Modifier.padding(spacing.medium)) {
            // Stylized Image Placeholder (Compact height 120.dp)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                colors.surfaceContainerHighest,
                                colors.background
                            )
                        )
                    )
                    .border(1.dp, colors.border.copy(alpha = 0.2f), RoundedCornerShape(6.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "[ IMAGE PLACEHOLDER ]",
                        style = MaterialTheme.typography.labelSmall,
                        color = colors.accent.copy(alpha = 0.5f),
                        fontFamily = FontFamily.Monospace,
                        letterSpacing = 1.sp,
                        fontSize = 9.sp
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = project.title.uppercase(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = colors.textSecondary.copy(alpha = 0.8f),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = project.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = colors.primary,
                    fontWeight = FontWeight.Bold
                )

                if (project.playStoreUrl != null) {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(colors.accent.copy(alpha = 0.1f))
                            .clickable { onOpenUrl(project.playStoreUrl) }
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "PLAY",
                            style = MaterialTheme.typography.labelSmall,
                            color = colors.accent,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                            fontSize = 9.sp
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                            contentDescription = "Open Play Store",
                            tint = colors.accent,
                            modifier = Modifier.size(10.dp)
                        )
                    }
                }
            }

            Spacer(Modifier.height(6.dp))

            Text(
                text = project.description,
                style = MaterialTheme.typography.bodySmall,
                color = colors.textSecondary,
                lineHeight = 18.sp,
                maxLines = 3
            )

            Spacer(Modifier.height(12.dp))

            // Bullet points rendered dynamically (Compact text size)
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                project.bullets.forEach { bullet ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            text = "•",
                            color = colors.accent,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                        Text(
                            text = bullet,
                            style = MaterialTheme.typography.bodySmall,
                            color = colors.textSecondary,
                            lineHeight = 16.sp
                        )
                    }
                }
            }

            // Tech stack chips (FlowRow)
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                project.techStack.forEach { tech ->
                    SkillChip(text = tech, variant = ChipVariant.Default)
                }
            }
        }
    }
}
