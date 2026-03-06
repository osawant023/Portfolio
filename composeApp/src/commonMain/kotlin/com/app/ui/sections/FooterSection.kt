package com.app.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.app.ui.components.TechBadge
import com.app.ui.theme.PortfolioTheme
import com.app.data.PortfolioData

@Composable
fun FooterSection(modifier: Modifier = Modifier) {
    val colors = PortfolioTheme.colors
    val spacing = PortfolioTheme.spacing

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    listOf(colors.cardBackground, Color(0xFF0A0E1A))
                )
            )
            .padding(horizontal = spacing.screenHorizontal)
            .padding(vertical = spacing.xlarge),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = PortfolioData.name,
            style = MaterialTheme.typography.headlineMedium,
            color = colors.textPrimary,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = PortfolioData.role,
            style = MaterialTheme.typography.bodyMedium,
            color = colors.primary
        )

        Spacer(Modifier.height(spacing.medium))

        TechBadge(text = "Built with Jetpack Compose")

        Spacer(Modifier.height(spacing.large))

        HorizontalDivider(color = colors.border)

        Spacer(Modifier.height(spacing.medium))

        Text(
            text = "© 2025 ${PortfolioData.name} · All rights reserved",
            style = MaterialTheme.typography.bodySmall,
            color = colors.textSecondary,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Designed & Developed with ❤️ in Mumbai",
            style = MaterialTheme.typography.labelSmall,
            color = colors.textSecondary.copy(alpha = 0.6f),
            textAlign = TextAlign.Center
        )
    }
}
