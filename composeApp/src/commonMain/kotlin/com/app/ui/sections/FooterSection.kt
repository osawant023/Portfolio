package com.app.ui.sections

import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.ui.theme.PortfolioTheme
import com.app.data.PortfolioData
import com.app.ui.components.LabelCapsText

@Composable
fun FooterSection(
    modifier: Modifier = Modifier,
    onOpenUrl: (String) -> Unit = {}
) {
    val colors = PortfolioTheme.colors
    val spacing = PortfolioTheme.spacing

    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        val isMobile = maxWidth < 768.dp

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.screenHorizontal)
                .padding(vertical = spacing.xxlarge)
        ) {
            HorizontalDivider(color = colors.border.copy(alpha = 0.15f))

            Spacer(Modifier.height(spacing.large))

            if (isMobile) {
                // Stack vertically on mobile screen configurations to prevent horizontal clipping
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LabelCapsText(
                            text = "Omkar Sawant",
                            color = colors.textSecondary,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = "© 2026 SENIOR ANDROID DEVELOPER. CRAFTED WITH PRECISION.",
                            style = MaterialTheme.typography.labelMedium,
                            color = colors.textSecondary.copy(alpha = 0.5f),
                            fontFamily = FontFamily.Monospace,
                            fontSize = 9.sp,
                            textAlign = TextAlign.Center
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        listOf(
                            "Github" to PortfolioData.githubUrl,
                            "LinkedIn" to PortfolioData.linkedInUrl
                        ).forEach { (label, url) ->
                            TextButton(
                                onClick = { onOpenUrl(url) },
                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = label,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = colors.textSecondary.copy(alpha = 0.7f),
                                    fontFamily = FontFamily.SansSerif
                                )
                            }
                        }
                    }
                }
            } else {
                // Side-by-side Row on desktop
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        LabelCapsText(
                            text = "Omkar Sawant",
                            color = colors.textSecondary,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = "© 2026 SENIOR ANDROID DEVELOPER. CRAFTED WITH PRECISION.",
                            style = MaterialTheme.typography.labelMedium,
                            color = colors.textSecondary.copy(alpha = 0.5f),
                            fontFamily = FontFamily.Monospace,
                            fontSize = 10.sp
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(spacing.large),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        listOf(
                            "Github" to PortfolioData.githubUrl,
                            "LinkedIn" to PortfolioData.linkedInUrl
                        ).forEach { (label, url) ->
                            TextButton(
                                onClick = { onOpenUrl(url) },
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Text(
                                    text = label,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = colors.textSecondary.copy(alpha = 0.7f),
                                    fontFamily = FontFamily.SansSerif
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
