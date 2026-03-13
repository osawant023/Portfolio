package com.app.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.app.data.PortfolioData
import com.app.ui.components.GradientButton
import com.app.ui.components.SectionHeader
import com.app.ui.theme.PortfolioTheme

@Composable
fun ContactSection(
    modifier: Modifier = Modifier,
    onEmailClick: () -> Unit = {},
    onOpenUrl: (String) -> Unit = {},
    onPhoneClick: () -> Unit = {}
) {
    val colors = PortfolioTheme.colors
    val spacing = PortfolioTheme.spacing

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = spacing.screenHorizontal)
            .padding(vertical = spacing.section),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SectionHeader(
            title = "Get in Touch",
            subtitle = "Let's build something amazing together"
        )

        Spacer(Modifier.height(spacing.large))

        // CTA card
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(
                    brush = Brush.linearGradient(
                        listOf(
                            colors.accentGradientStart.copy(0.1f),
                            colors.accentGradientEnd.copy(0.1f)
                        )
                    )
                )
                .border(
                    width = 1.dp,
                    brush = Brush.horizontalGradient(
                        listOf(colors.accentGradientStart, colors.accentGradientEnd)
                    ),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(spacing.large),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "I'm currently open to new opportunities",
                style = MaterialTheme.typography.headlineSmall,
                color = colors.textPrimary,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(spacing.small))
            Text(
                text = "Whether you have a role in mind, a project to discuss, or just want to connect — my inbox is open.",
                style = MaterialTheme.typography.bodyMedium,
                color = colors.textSecondary,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(spacing.large))
            GradientButton(
                text = "Say Hello 👋",
                onClick = onEmailClick
            )
        }

        Spacer(Modifier.height(spacing.large))

        // Contact links
        Column(
            verticalArrangement = Arrangement.spacedBy(spacing.small),
            modifier = Modifier.fillMaxWidth()
        ) {
            ContactRow(
                icon = Icons.Default.Email,
                label = "Email",
                value = PortfolioData.email,
                onClick = onEmailClick
            )
            ContactRow(
                icon = Icons.Default.Person,
                label = "LinkedIn",
                value = PortfolioData.linkedIn,
                onClick = {onOpenUrl(PortfolioData.linkedInUrl)}
            )
            ContactRow(
                icon = Icons.Default.Code,
                label = "GitHub",
                value = PortfolioData.github,
                onClick = {
                    onOpenUrl(PortfolioData.githubUrl)
                }
            )
            ContactRow(
                icon = Icons.Default.Phone,
                label = "Phone",
                value = PortfolioData.phone,
                onClick = onPhoneClick
            )
        }
    }
}

@Composable
private fun ContactRow(
    icon: ImageVector,
    label: String,
    value: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = PortfolioTheme.colors
    val spacing = PortfolioTheme.spacing

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(colors.cardBackground)
            .border(1.dp, colors.border, RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(spacing.medium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing.medium)
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(colors.primary.copy(0.12f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = colors.primary,
                modifier = Modifier.size(18.dp)
            )
        }
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = colors.textSecondary
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = colors.textPrimary,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
