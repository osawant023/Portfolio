package com.app.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.VerifiedUser
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
import com.app.ui.components.SectionHeader
import com.app.ui.theme.PortfolioTheme
import com.app.data.PortfolioData

@Composable
fun CertificationsSection(modifier: Modifier = Modifier) {
    val colors = PortfolioTheme.colors
    val spacing = PortfolioTheme.spacing

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = spacing.screenHorizontal)
            .padding(vertical = spacing.section)
    ) {
        SectionHeader(
            title = "Certifications",
            subtitle = "Continuous learning and growth"
        )

        Spacer(Modifier.height(spacing.large))

        PortfolioData.certifications.forEach { cert ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(colors.cardBackground)
                    .border(
                        1.dp,
                        Brush.horizontalGradient(
                            listOf(colors.accentGradientStart, colors.accentGradientEnd)
                        ),
                        RoundedCornerShape(12.dp)
                    )
                    .padding(spacing.medium),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(spacing.medium)
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(colors.primary.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.VerifiedUser,
                        contentDescription = null,
                        tint = colors.primary,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = cert.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = colors.textPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = cert.issuer,
                        style = MaterialTheme.typography.bodySmall,
                        color = colors.textSecondary
                    )
                }

                if (cert.credentialUrl != null) {
                    Icon(
                        imageVector = Icons.Default.OpenInNew,
                        contentDescription = "View credential",
                        tint = colors.primary,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}
