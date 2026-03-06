package com.app.ui.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.ui.components.ChipVariant
import com.app.ui.components.PortfolioCard
import com.app.ui.components.SectionHeader
import com.app.ui.components.SkillChip
import com.app.ui.theme.PortfolioTheme
import com.app.data.PortfolioData

@Composable
fun SkillsSection(modifier: Modifier = Modifier) {
    val colors = PortfolioTheme.colors
    val spacing = PortfolioTheme.spacing

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = spacing.section)
    ) {
        // Header with padding
        Column(modifier = Modifier.padding(horizontal = spacing.screenHorizontal)) {
            SectionHeader(
                title = "Skills",
                subtitle = "Technologies and tools I work with"
            )
        }

        Spacer(Modifier.height(spacing.large))

        // Skill categories — horizontal scrolling rows
        PortfolioData.skillCategories.forEach { (category, skills) ->
            Column(modifier = Modifier.padding(bottom = spacing.medium)) {
                Text(
                    text = category.uppercase(),
                    style = MaterialTheme.typography.labelMedium,
                    color = colors.textSecondary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        horizontal = spacing.screenHorizontal,
                        vertical = 4.dp
                    )
                )
                Spacer(Modifier.height(spacing.small))
                LazyRow(
                    contentPadding = PaddingValues(horizontal = spacing.screenHorizontal),
                    horizontalArrangement = Arrangement.spacedBy(spacing.small)
                ) {
                    items(skills) { skill ->
                        SkillChip(
                            text = skill,
                            variant = when (category) {
                                "Languages" -> ChipVariant.Accent
                                "Architecture" -> ChipVariant.Default
                                else -> ChipVariant.Alt
                            }
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(spacing.medium))

        // Full skills cloud card
        Column(modifier = Modifier.padding(horizontal = spacing.screenHorizontal)) {
            PortfolioCard {
                Column {
                    Text(
                        text = "All Technologies",
                        style = MaterialTheme.typography.titleMedium,
                        color = colors.textPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.height(spacing.medium))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(spacing.small),
                        verticalArrangement = Arrangement.spacedBy(spacing.small)
                    ) {
                        PortfolioData.skills.forEach { skill ->
                            SkillChip(text = skill, variant = ChipVariant.Alt)
                        }
                    }
                }
            }
        }
    }
}
