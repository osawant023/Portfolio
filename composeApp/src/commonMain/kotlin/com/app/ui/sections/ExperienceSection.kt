package com.app.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.ui.components.SectionHeader
import com.app.ui.theme.PortfolioTheme
import com.app.data.Experience
import com.app.data.PortfolioData

@Composable
fun ExperienceSection(modifier: Modifier = Modifier) {
    val colors = PortfolioTheme.colors
    val spacing = PortfolioTheme.spacing

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = spacing.screenHorizontal)
            .padding(vertical = spacing.section)
    ) {
        SectionHeader(
            title = "Experience",
            subtitle = "My professional journey"
        )

        Spacer(Modifier.height(spacing.large))

        PortfolioData.experiences.forEachIndexed { index, experience ->
            TimelineItem(
                experience = experience,
                isLast = false
            )
        }
    }
}

@Composable
private fun TimelineItem(
    experience: Experience,
    isLast: Boolean,
    modifier: Modifier = Modifier
) {
    val colors = PortfolioTheme.colors
    val spacing = PortfolioTheme.spacing


    Row(modifier = modifier.fillMaxWidth().height(IntrinsicSize.Min)  ) {
        // Timeline column
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(28.dp)
        ) {
            // Dot
            Box(
                modifier = Modifier
                    .size(14.dp)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(colors.primary, colors.accentGradientEnd)
                        ),
                        shape = CircleShape
                    )
                    .border(2.dp, colors.background, CircleShape)
            )

            // Connecting line
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .fillMaxHeight()
                        .padding(bottom = spacing.xlarge)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(colors.timelineLine, colors.border)
                            )
                        )
                )
            }
        }

        Spacer(Modifier.width(spacing.medium))

        // Content
        Column(modifier = Modifier.weight(1f)) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = experience.company,
                        style = MaterialTheme.typography.headlineSmall,
                        color = colors.textPrimary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = experience.location,
                        style = MaterialTheme.typography.bodySmall,
                        color = colors.textSecondary
                    )
                }
                if (experience.isCurrent) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(colors.primary.copy(alpha = 0.15f))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "Current",
                            color = colors.primary,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp
                        )
                    }
                }
            }

            Spacer(Modifier.height(4.dp))

            // Role & Period
            Text(
                text = experience.role,
                style = MaterialTheme.typography.titleLarge,
                color = colors.primary,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = experience.period,
                style = MaterialTheme.typography.labelMedium,
                color = colors.textSecondary
            )

            Spacer(Modifier.height(spacing.medium))

            // Highlights
            experience.highlights.forEach { highlight ->
                Row(
                    modifier = Modifier.padding(bottom = 6.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(top = 7.dp)
                            .size(5.dp)
                            .background(colors.primary.copy(0.7f), CircleShape)
                    )
                    Text(
                        text = highlight,
                        style = MaterialTheme.typography.bodyMedium,
                        color = colors.textSecondary
                    )
                }
            }

            Spacer(Modifier.height(spacing.xlarge))
        }
    }
}
