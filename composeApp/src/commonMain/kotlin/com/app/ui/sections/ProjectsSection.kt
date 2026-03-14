package com.app.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.data.PortfolioData
import com.app.data.Project
import com.app.ui.components.ChipVariant
import com.app.ui.components.SectionHeader
import com.app.ui.components.SkillChip
import com.app.ui.theme.PortfolioTheme

@Composable
fun ProjectsSection(modifier: Modifier = Modifier , onOpenUrl : (String) -> Unit = {}) {
    val colors = PortfolioTheme.colors
    val spacing = PortfolioTheme.spacing

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = spacing.screenHorizontal)
            .padding(vertical = spacing.section)
    ) {
        SectionHeader(
            title = "Projects",
            subtitle = "Apps and features I've built"
        )

        Spacer(Modifier.height(spacing.large))

        PortfolioData.projects.forEachIndexed { index, project ->
            ProjectCard(
                project = project,
                index = index,
                onOpenUrl = onOpenUrl
            )
            Spacer(Modifier.height(spacing.medium))
        }
    }
}

@Composable
private fun ProjectCard(
    project: Project,
    index: Int,
    modifier: Modifier = Modifier,
    onOpenUrl : (String) -> Unit = {}
) {
    val colors = PortfolioTheme.colors
    val spacing = PortfolioTheme.spacing

    // Alternate gradient accent for variety
    val accentColors = listOf(
        Pair(colors.accentGradientStart, colors.accentGradientEnd),
        Pair(colors.secondary, colors.accentGradientStart),
        Pair(colors.accent, colors.secondary),
        Pair(colors.accentGradientEnd, colors.accent),
        Pair(colors.accentGradientStart, colors.accent)
    )
    val (gradStart, gradEnd) = accentColors[index % accentColors.size]

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(colors.cardBackground)
            .border(1.dp, colors.border, RoundedCornerShape(16.dp))
    ) {
        // Gradient accent bar at top
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(gradStart, gradEnd))
                )
        )

        Column(modifier = Modifier.padding(spacing.cardPadding)) {
            // Title row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = project.title,
                        style = MaterialTheme.typography.headlineSmall,
                        color = colors.textPrimary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = project.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = colors.textSecondary
                    )
                }

                if (project.playStoreUrl != null) {
                    Spacer(Modifier.width(spacing.small))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(colors.chipBackground)
                            .border(1.dp, colors.chipBorder, RoundedCornerShape(8.dp))
                            .padding(8.dp)
                            .clickable{
                                onOpenUrl(project.playStoreUrl)
                            }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.OpenInNew,
                                contentDescription = "Play Store",
                                tint = colors.primary,
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                text = "Link",
                                color = colors.primary,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(spacing.medium))

            // Bullet highlights
            project.bullets.forEach { bullet ->
                Row(
                    modifier = Modifier.padding(bottom = 5.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(top = 7.dp)
                            .size(4.dp)
                            .background(
                                brush = Brush.radialGradient(listOf(gradStart, gradEnd)),
                                shape = CircleShape
                            )
                    )
                    Text(
                        text = bullet,
                        style = MaterialTheme.typography.bodySmall,
                        color = colors.textSecondary
                    )
                }
            }

            Spacer(Modifier.height(spacing.medium))

            // Tech stack chips
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(spacing.small),
                verticalArrangement = Arrangement.spacedBy(spacing.small)
            ) {
                project.techStack.forEach { tech ->
                    SkillChip(text = tech, variant = ChipVariant.Default)
                }
            }
        }
    }
}
