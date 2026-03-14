package com.app.ui.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.ui.components.PortfolioCard
import com.app.ui.components.SectionHeader
import com.app.ui.components.StatCard
import com.app.ui.theme.PortfolioTheme
import com.app.data.PortfolioData

@Composable
fun AboutSection(modifier: Modifier = Modifier) {
    val colors = PortfolioTheme.colors
    val spacing = PortfolioTheme.spacing

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = spacing.screenHorizontal)
            .padding(vertical = spacing.section)
    ) {
        SectionHeader(
            title = "About Me",
            subtitle = "Who I am and what drives me"
        )

        Spacer(Modifier.height(spacing.large))

        PortfolioCard {
            Text(
                text = PortfolioData.aboutSummary,
                style = MaterialTheme.typography.bodyLarge,
                color = colors.textSecondary,
                lineHeight = MaterialTheme.typography.bodyLarge.lineHeight
            )
        }

        Spacer(Modifier.height(spacing.large))

        // Stats grid
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(spacing.small)
        ) {
            PortfolioData.aboutStats.forEach { (value, label) ->
                StatCard(
                    value = value,
                    label = label,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(Modifier.height(spacing.large))

        // Location / contact strip
        PortfolioCard {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = PortfolioData.location,
                        style = MaterialTheme.typography.bodyMedium,
                        color = colors.textSecondary
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = PortfolioData.phone,
                        style = MaterialTheme.typography.bodyMedium,
                        color = colors.textSecondary
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "Available for",
                        style = MaterialTheme.typography.labelSmall,
                        color = colors.textSecondary
                    )
                    Text(
                        text = "Full-time / Contract",
                        style = MaterialTheme.typography.bodyMedium,
                        color = colors.primary,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
